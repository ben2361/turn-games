package to.ax.games.util;

import java.util.List;

import to.ax.games.Rules;

/**
 * @author Tom Ritchford (tom@swirly.com)
 * Run a game until it ends.
 */
abstract public class GameRunner<Game, Move, TerminalState>  {
  public GameRunner(Rules<Game, Move, TerminalState> rules) {
    this.rules = rules;
  }
  
  public TerminalState runGame() {
    game = getInitialGame();
    while (true) {
      List<Move> moves = getLegalMoves(game); 
      if (moves.size() == 0)
        return getTerminalState();
      Move move = selectNextMove(moves);
      game = applyMove(move);
    } 
  }
  
  abstract protected Move selectNextMove(List<Move> moves);

  // These next methods are for your convenience squares override.  
  protected Game getInitialGame() { 
    return rules.getInitialGameState();
  }
  protected List<Move> getLegalMoves(Game game) { 
    return rules.getLegalMoves(game);    
  }
  protected Game applyMove(Move move) { 
    return rules.applyMove(game, move);  
  }
  protected TerminalState getTerminalState() { 
    return rules.getTerminalState(game);
  }

  private final Rules<Game, Move, TerminalState> rules;
  Game game;
}
