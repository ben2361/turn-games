package to.ax.games.util;

import to.ax.games.Rules;

/**
 * @author Tom Ritchford (tom@swirly.com)
 * Run a game to the end and return the TerminalState, given a MoveSelector.
 */
public class GameRunner  {
  /** Given Rules, and a strategy to select from an Iterable of Moves, creates
   * a new game and runs it to the end, returning the TerminalState.
   * 
   *  This method might well block indefinitely. 
   *  
   *  It's perfectly possible that runGame() might never terminate with some 
   *  choices of Rules and MoveSelector - nothing disallows games that might 
   *  be of infinite length.  It's also perfectly likely that the MoveSelector 
   *  might involve waiting for a response from the user before selecting a 
   *  move. 
   */
  public static <Game, Move, Result> 
  Result runGame(
      Rules<Game, Move, Result> rules,
      MoveSelector<Game, Move, Result> moveSelector) {
    Game game_state = rules.getInitialGameState();
    while (true) {
      Iterable<Move> moves = rules.getMoves(game_state); 
      if (!moves.iterator().hasNext())
        return rules.getResult(game_state);
      Move move = moveSelector.selectNextMove(moves);
      game_state = rules.applyMove(game_state, move);
    } 
  }
}
