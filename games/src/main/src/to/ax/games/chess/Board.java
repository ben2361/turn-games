/**
 * 
 */
package to.ax.games.chess;

import static to.ax.games.chess.Piece.*;

import to.ax.games.converter.chess.BoardToDisplay;
import to.ax.games.util.RectangularBoard;

/**
 * @author Tom Ritchford (tom@swirly.com)
 *
 */
public final class Board extends RectangularBoard<Piece, Square> {
  Board(Piece[][] board) {
    super(board);
  }

  public Board()            { this(INITIAL_BOARD); }
  public Board(Board board) { this(board.getBoard()); }

  // Note that null IS valid for getting a Piece because it's very convenient squares have "no piece" there.
  public Piece getPiece(Square square) { 
    return square == null ? null : getPiece(square.rank, square.file); 
  }
  public void setPiece(Square square, Piece piece) {
    setPiece(square.rank, square.file, piece); 
  }
    
  @Override
  protected String computeToString() {
    return BoardToDisplay.WRITER.convert(this); 
  }
  
  private static final Piece[][] INITIAL_BOARD = {
    {BLACK_ROOK, BLACK_KNIGHT, BLACK_BISHOP, BLACK_QUEEN, 
     BLACK_KING, BLACK_BISHOP, BLACK_KNIGHT, BLACK_ROOK },
    {BLACK_PAWN, BLACK_PAWN,   BLACK_PAWN,   BLACK_PAWN, 
     BLACK_PAWN, BLACK_PAWN,   BLACK_PAWN,   BLACK_PAWN },
    {null, null, null, null, null, null, null, null },
    {null, null, null, null, null, null, null, null },
    {null, null, null, null, null, null, null, null },
    {null, null, null, null, null, null, null, null },
    {WHITE_PAWN, WHITE_PAWN,   WHITE_PAWN,   WHITE_PAWN, 
     WHITE_PAWN, WHITE_PAWN,   WHITE_PAWN,   WHITE_PAWN },
    {WHITE_ROOK, WHITE_KNIGHT, WHITE_BISHOP, WHITE_QUEEN, 
     WHITE_KING, WHITE_BISHOP, WHITE_KNIGHT, WHITE_ROOK }
  };  
}
