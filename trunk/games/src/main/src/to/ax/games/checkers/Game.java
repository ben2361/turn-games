/**
 * 
 */
package to.ax.games.checkers;

import to.ax.games.util.CachedHash;
/**
 * @author Tom Ritchford (tom@swirly.com)
 * This is an immutable class.
 *
 */
public class Game extends CachedHash<Game> {  
  /** The checkers board. */
  private final Board board;
  
  public Game(Board board) {
    this.board = board;
  }
  
  public Board getBoardCopy() { return new Board(this.board); }
  
  public Board getBoard() { return this.board; }

  @Override
  protected String computeToString() {
    return ""; // GameToString.WRITER.convert(this);
  }
}
