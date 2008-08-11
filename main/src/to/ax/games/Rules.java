package to.ax.games;

import java.awt.Container;
import java.util.Collection;
import java.util.Set;

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
  /** @return the unique initial game state.  This might well be a singleton if the GameState is
   * an immutable class. */
  GameState getInitialGameState();    
  
  /** There are two distinct ways to "get legal moves":  the first one is to return an explicit
   * list of the legal moves;  the second is to provide a way of validating a Move that is given to
   * you.  I'm providing both;  you can use the first one to make the second;  we'll see later if
   * we need to split these functionalities more decisively. */
  Iterable<Move> getMoves(GameState gameState);
  
  /** @return true if a given move is valid for a given game. */
  boolean isMoveValid(GameState gameState, Move move);
  
  /** Given a GameState and a legal move for that Game state, return a GameState representing
   * the new state of the game after the move has been applied. */
  GameState applyMove(GameState gameState, Move move);
  
  // If getLegalMoves(game) is empty, then you can call getResult().
  Result getResult(GameState gameState);
}
