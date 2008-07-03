package to.ax.games;

import java.util.List;

/** Generic rules for turn-based games.  The idea:
 *  See squares.ax.games.util.GameRunner.runGame for a typical usage.
 * */

public interface Rules<GameState, Move, TerminalState> {
  GameState getInitialGameState();    
  List<Move> getLegalMoves(GameState gameState);
  GameState applyMove(GameState gameState, Move move);

  // If getLegalMoves(game) is empty, then you can call getTerminalState().
  TerminalState getTerminalState(GameState game);
}
