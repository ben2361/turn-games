package to.ax.games.util;

/**
 * @author Tom Ritchford (tom@swirly.com)
 *
 * @param <GameState>
 * @param <Move>
 * @param <TerminalState>
 */
public interface MoveSelector<GameState, Move, TerminalState> {

  /** Select a next move from an Iterable of next moves. */
  public abstract Move selectNextMove(Iterable<Move> moves);

}