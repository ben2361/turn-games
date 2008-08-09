package to.ax.games.converter.chess;

import static to.ax.games.chess.Piece.*;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import to.ax.games.chess.Piece;
import to.ax.games.converter.Converter;


/**
 * @author Tom Ritchford (tom@swirly.com)
 *
 */
public class PieceToCharacter implements Converter.TwoWay<Piece, Character> {
  private PieceToCharacter() {}
  public static final PieceToCharacter INSTANCE = new PieceToCharacter();
  
  public Converter<Character, Piece> getConvertToFrom() { return READER; }  
  public Converter<Piece, Character> getConvertFromTo() { return WRITER; }  
  
  public static final Converter<Character, Piece> READER = new Converter<Character, Piece>() {
    public Piece convert(Character from) {
      return SYMBOL_PIECES.get(from);      
    }    
  };
  
  public static final Converter<Piece, Character> WRITER = new Converter<Piece, Character>() {
    public Character convert(Piece from) {
      return (from == null) ? '.' : PIECE_SYMBOLS.get(from);
    }    
  };
  
  private static final Map<Piece, Character> PIECE_SYMBOLS 
      = new EnumMap<Piece, Character>(Piece.class);
  private static final Map<Character, Piece> SYMBOL_PIECES 
    = new HashMap<Character, Piece>();

  private static void putPieceSymbol(Piece piece, char symbol) {
    PIECE_SYMBOLS.put(piece, symbol);
    SYMBOL_PIECES.put(symbol, piece);
  }
    
  static {
    putPieceSymbol(BLACK_PAWN,   'p');
    putPieceSymbol(BLACK_KNIGHT, 'n');
    putPieceSymbol(BLACK_BISHOP, 'b');
    putPieceSymbol(BLACK_ROOK,   'r');
    putPieceSymbol(BLACK_QUEEN,  'q');
    putPieceSymbol(BLACK_KING,   'k');
    putPieceSymbol(WHITE_PAWN,   'P');
    putPieceSymbol(WHITE_KNIGHT, 'N');
    putPieceSymbol(WHITE_BISHOP, 'B');
    putPieceSymbol(WHITE_ROOK,   'R');
    putPieceSymbol(WHITE_QUEEN,  'Q');
    putPieceSymbol(WHITE_KING,   'K');
  }
}
