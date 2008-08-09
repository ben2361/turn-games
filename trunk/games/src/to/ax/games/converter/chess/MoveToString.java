package to.ax.games.converter.chess;

import java.util.regex.Matcher;

import to.ax.games.chess.Square;
import to.ax.games.chess.Move;
import to.ax.games.chess.Piece;
import to.ax.games.converter.Converter;

/**
 * @author Tom Ritchford (tom@swirly.com)
 *
 */
public class MoveToString implements Converter.TwoWay<Move, String> {

  private MoveToString() {}
  public static final MoveToString INSTANCE = new MoveToString();
  
  public Converter<String, Move> getConvertToFrom() { return READER; }  
  public Converter<Move, String> getConvertFromTo() { return WRITER; }
  
  private static final Converter<String, Move> READER = new Converter<String, Move>() {
    public Move convert(String str) {
      Matcher matcher = GameMoveToString.MATCH_MOVE.matcher(str);
      if (!matcher.matches()) 
        return null;

      Square from = Square.valueOf(matcher.group(2).toUpperCase());      
      Square to = Square.valueOf(matcher.group(4).toUpperCase());
      boolean isEp = false;
      Piece promotion = null;
      
      String more = matcher.group(6);
      if (more != null) {
        if (more.equals("e.p.")) {
          isEp = true;
        } else {
          if (more.length() != 1)
            return null;  // Should never happen.
          promotion = PieceToCharacter.READER.convert(more.charAt(0));
          if (promotion == null)
            return null;
        }
      }
      return new Move(from, to, promotion, isEp);
    }
  };
  
  public static final Converter<Move, String> WRITER = new Converter<Move, String>() {
    public String convert(Move move) {
      StringBuffer b = new StringBuffer(move.from + "-" + move.to);
      if (move.promotionPiece != null) {
        b.append(" (");
        b.append(PieceToCharacter.WRITER.convert(move.promotionPiece));
        b.append(")");      
      }
      if (move.enPassant)
        b.append(" (e.p.)");
      return b.toString();
    }
  };
}