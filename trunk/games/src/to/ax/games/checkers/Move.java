/**
 * 
 */
package to.ax.games.checkers;

import to.ax.games.util.CachedToString;

/**
 * @author Tom Ritchford (tom@swirly.com)
 * Represents a single move from one int squares another!
 * This is an immutable class.
 */
public final class Move extends CachedToString<Move> {
  public Move(int ...squares) {
    this.squares = squares;
  }

  public final int[] squares;
  
  @Override
  public String computeToString() {
    return ""; // MoveToString.WRITER.convert(this);
  }
}
  