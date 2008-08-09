/**
 * 
 */
package to.ax.games.util;

import to.ax.games.chess.Square;

/**
 * @author Tom Ritchford (tom@swirly.com)
 *
 */
@SuppressWarnings("hiding")
abstract public class RectangularBoard<Piece, Square> 
    extends CachedToString<RectangularBoard<Piece, Square>>
    implements Board<Piece, Square> {
  public RectangularBoard(Piece[][] board) {
    this.board = cloneBoard(board);
  }

  public RectangularBoard(RectangularBoard<Piece, Square> that) {
    this.board = cloneBoard(that.board);
  }

  private Piece[][] cloneBoard(Piece[][] board) {
    final Piece[][] newBoard = (Piece[][])board.clone();
    for (int i = 0; i < board.length; i++) 
      board[i] = (Piece[]) board[i].clone();
    return newBoard;
  }
  
  public Piece getPiece(int x, int y) { 
    return board[x][y];
  }
  
  public void setPiece(int x, int y, Piece piece) { 
    board[x][y] = piece; 
    invalidateToString();
  }
  
  private Piece[][] board;
  
  protected Piece[][] getBoard() { return board; }  

  abstract protected String computeToString();
  abstract public Piece getPiece(Square index);
  abstract public void setPiece(Square index, Piece piece);
}
