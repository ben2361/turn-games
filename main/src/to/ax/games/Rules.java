package to.ax.games;

/** Generic rules for turn-based games.
 * 
 * Informally, a turn-based game is a game whose rules are essentially time-
 * independent, where the essential(*) state of the game changes in discrete 
 * increments called Moves.
 * 
 * This includes every board and card game including puzzles and solitaire.
 * This excludes arcade games, first person shooters or any animated game.
 * 
 * The challenge is to come up with a generic interface that's specific enough
 * that an instance will completely define the rules of a game, but generic
 * enough that it will cover all turn-based games.
 * 
 * Rules is generic in three classes:
 *   GameState:  the board position or state of the game.
 *   Move:  a Move from a specific GameState.
 *   Result:  the outcome of a completed game.
 *   
 *  The Rules are the only ones who can construct a Move for a GameState.
 *  This allows the GameState to be re-useable, for example a "deck of cards",
 *  and have many different Rules on top of it.
 *   
 *  @see Squares.ax.games.util.GameRunner.runGame to see how a game is run.
 *  
 * ((*) - "essential":  in many common games like chess and Go, there is a clock 
 * going, so the state is changing as time passes.  
 * 
 * I define the clock as "not essential":  you always have the same possible choice 
 * of moves whether you have 30 minutes or 30 seconds left on the clock.)
 * 
 * */

public interface Rules<GameState, Move, Result> {
  GameState getInitialGameState();    
  Iterable<Move> getMoves(GameState gameState);
  GameState applyMove(GameState gameState, Move move);

  // If getLegalMoves(game) is empty, then you can call getResult().
  Result getResult(GameState gameState);
}
