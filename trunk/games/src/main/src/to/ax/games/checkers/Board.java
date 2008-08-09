/**
 * 
 */
package to.ax.games.checkers;

import to.ax.games.checkers.rules.CheckersRules;
import to.ax.games.checkers.rules.Variant;
import to.ax.games.util.RectangularBoard;

/**
 * @author Tom Ritchford (tom@swirly.com)
 *
 */
public final class Board extends RectangularBoard<Piece, Square>{
  public Board(Variant variant, Piece[][] board) {
    super(board);
    this.variant = variant;
  }

  public final Variant variant;
  
  public Board(Board board) { this(board.variant, null); }

  @Override
  public Piece getPiece(Square square) {
    return CheckersRules.getPiece(variant, getBoard(), square);
  }
  
  @Override
  public void setPiece(Square square, Piece piece) {
    CheckersRules.setPiece(variant, getBoard(), square, piece);
  }
  
  @Override
  protected String computeToString() {
    return ""; // BoardToDisplay.WRITER.convert(this); 
  }
}
