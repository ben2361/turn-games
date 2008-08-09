package to.ax.games.converter.chess;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import to.ax.games.chess.Square;
import to.ax.games.chess.Game;
import to.ax.games.chess.Move;
import to.ax.games.chess.Piece;
import to.ax.games.chess.rules.ChessRules;
import to.ax.games.converter.Converter;
import to.ax.games.util.Color;
import to.ax.games.util.Filter;

import static to.ax.games.chess.Square.*;
import static to.ax.games.util.Color.*;


/**
 * @author Tom Ritchford (tom@swirly.com)
 *
 */
public class GameMoveToString implements Converter.TwoWay<Move, String>{
  private static final String QUEENS_CASTLE = "O-O-O";
  private static final String KINGS_CASTLE = "O-O";

  protected final Game game;
  public GameMoveToString(Game game) {
    this.game = game;
    this.moves = ChessRules.INSTANCE.getMoves(game);
  }

  public Converter<Move, String> getConvertFromTo() { return writer; }  
  
  public final Converter<Move, String> writer = new Converter<Move, String>() {
    public String convert(Move move) {
      // Check for castling.
      final Square to = move.to;
      final Square from = move.from;
      final Piece fromPiece = game.getPiece(from);
      if (fromPiece.type == Piece.Type.KING) {
        int files = move.to.file - move.from.file;
        if (files == 2)
          return KINGS_CASTLE;
        else if (files == -2)
          return QUEENS_CASTLE;
      }
      String toString = to.toString().toLowerCase();
      Filter<Move> toFilter = new Filter<Move>() {
        public boolean accepts(Move legal) {
          return legal.to == to;
        }
      };
      
      if (isUniqueMoveForFilter(toFilter))
        return toString;
      
      final boolean enPassant = move.enPassant;
      char capture = game.getPiece(to) != null || enPassant ? 'x' : '-';
      String partial = capture + toString;
      
      Filter<Move> toPieceFilter = new Filter<Move>() {
        public boolean accepts(Move legal) {
          return legal.to == to && game.getPiece(legal.from) == fromPiece;
        }
      };
      if (isUniqueMoveForFilter(toPieceFilter))
        return (getSymbolFromPiece(fromPiece) + partial).toLowerCase(); 

      String fromString = from.toString().toLowerCase() + partial;
      Filter<Move> toFromFilter = new Filter<Move>() {
        public boolean accepts(Move legal) {
          return legal.to == from && legal.from == to;
        }
      };
      if (isUniqueMoveForFilter(toFromFilter))
        return fromString;
      
      // The only reason why a move would still not be unique at this point is some pawn
      // perversity -- either e.p. or promotion.
      final Piece promotionPiece = move.promotionPiece;
      String suffix;
      boolean unique;
      if (promotionPiece != null) {
        suffix = (" (" + getSymbolFromPiece(promotionPiece) + ")").toLowerCase();
        Filter<Move> promotionFilter = new Filter<Move>() {
          public boolean accepts(Move legal) {
            return legal.to == to && legal.promotionPiece == promotionPiece;
          }
        };
        unique = isUniqueMoveForFilter(promotionFilter);
      } else {
        // Must be en passant!  How squares verify this though?!
        suffix = enPassant ? " (e.p.)" : "";
        Filter<Move> enPassantFilter = new Filter<Move>() {
          public boolean accepts(Move legal) {
            return legal.to == to && legal.enPassant == enPassant;
          }
        };
        unique = isUniqueMoveForFilter(enPassantFilter);
      }
      return (unique ? toString : fromString) + suffix;
    }

    private Character getSymbolFromPiece(final Piece promotionPiece) {
      return Character.toUpperCase(PieceToCharacter.WRITER.convert(promotionPiece));
    }    
  };
  
  public Converter<String, Move> getConvertToFrom() { return reader; }  
  public final Converter<String, Move> reader = new Converter<String, Move>() {
    public Move convert(String str) {
      Color color = ChessRules.getColorToMove(game);
      final boolean isKingsCastle = str.equalsIgnoreCase(KINGS_CASTLE);
      final boolean isQueensCastle = str.equalsIgnoreCase(QUEENS_CASTLE);
      if (isKingsCastle || isQueensCastle) {
        final Square from = (color == WHITE) ? E1 : E8;
        final Square to = from.next(isKingsCastle ? 2 : -2);
        return returnLegalMove(new Move(from, to));
      }
      
      Matcher matcher = GameMoveToString.MATCH_MOVE.matcher(str);
      if (!matcher.matches()) 
        return null;
      
      final Square to = Square.valueOf(matcher.group(4).toUpperCase());
      String more = matcher.group(6);
      final boolean enPassant = "e.p.".equalsIgnoreCase(more);
      final Piece promotionPiece;
      
      if (more == null || enPassant) {
        promotionPiece = null;
      } else {
        if (more.length() != 1)
          return null;  // Should never happen.
        promotionPiece = getPieceFromSymbol(color, more);
        if (promotionPiece == null)
          return null;
      }
      final String fromStr = matcher.group(2);
      if (fromStr == null) {
        Filter<Move> filter = new Filter<Move>() {
          public boolean accepts(Move move) {
            return move.to == to && move.promotionPiece == promotionPiece && 
                move.enPassant == enPassant;
          }
        };
        return getUniqueMoveForFilter(filter);
      } else {
        int length = fromStr.length();
        if (length == 2) {          
          final Square from = Square.valueOf(fromStr.toUpperCase());
          return returnLegalMove(new Move(from, to, promotionPiece, enPassant));
        } else if (length == 1) {
          final Piece piece = getPieceFromSymbol(color, fromStr);
          if (piece == null)
            return null;
          Filter<Move> filter = new Filter<Move>() {
            public boolean accepts(Move move) {
              return move.to == to && move.promotionPiece == promotionPiece && 
                  move.enPassant == enPassant && game.getPiece(move.from) == piece;
             }
          };
          return getUniqueMoveForFilter(filter);
        } else {
          return null;
        }
      }
    }

    private Move returnLegalMove(final Move newMove) {
      for (Move move: moves) 
        if (move.equals(newMove))
          return move;
      return null;
    }

    private Piece getPieceFromSymbol(Color color, String fromStr) {
      char ch = fromStr.charAt(0);
      return Piece.getPiece(PieceToCharacter.READER.convert(ch).type, color);
    }
  };

  public boolean isUniqueMoveForFilter(Filter<Move> filter) {
    return getUniqueMoveForFilter(filter) != null;
  }
  
  public Move getUniqueMoveForFilter(Filter<Move> filter) {
    Move move = null;
    for (Move m : moves) {
      if (filter.accepts(m)) {
        if (move == null)
          move = m;
        else
          return null;
      }
    }
    return move;
  }
  
  private final List<Move> moves;
  public static final Pattern MATCH_MOVE = Pattern.compile(
      "(([a-h][0-9]|[pnbrqk])([-x]))?([a-h][0-9])( \\(((e\\.p\\.)|.)\\))?",
      Pattern.CASE_INSENSITIVE);
}
