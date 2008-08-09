/**
 * 
 */
package to.ax.games.chess;

import to.ax.games.chess.rules.ChessValidator;
import to.ax.games.converter.chess.MoveToString;
import to.ax.games.util.CachedToString;
/**
 * @author Tom Ritchford (tom@swirly.com)
 * Represents a move from one square squares another, with an optional piece and an optional
 * "is en passant" flag!
 * This is an immutable class.
 */
public final class Move extends CachedToString<Move> {
  public Move(Square from, Square to, Piece promotionPiece, boolean enPassant) {
    this.from = from;
    this.to = to;
    this.promotionPiece = promotionPiece;
    this.enPassant = enPassant;
    
    ChessValidator.INSTANCE.checkMove(this);
  }

  public Move(Square from, Square to, Piece toPiece) { this(from, to, toPiece, false); }
  public Move(Square from, Square to) { this(from, to, null);  }
  
  public final Square from;
  public final Square to;
  public final Piece promotionPiece;
  public boolean enPassant;
  
  @Override
  public String computeToString() {
    return MoveToString.WRITER.convert(this);
  }
}
  