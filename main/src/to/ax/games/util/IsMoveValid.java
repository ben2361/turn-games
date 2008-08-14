package to.ax.games.util;

import java.util.Collection;

import to.ax.games.Rules;

/**
 * @author Tom Ritchford (tom@swirly.com)
 *
 */
public class IsMoveValid {
  public static <GameState, Move, Result> 
  boolean isMoveValid(GameState gameState, Move move, Rules<GameState, Move, Result> rules) {
    return isItemInIterable(move, rules.getLegalMoves(gameState));
  }

  public static <Item> boolean isItemInIterable(Item move, Iterable<Item> moves) {
    if (moves instanceof Collection) 
      return ((Collection<Item>) moves).contains(move);
    
    for (Item legalMove: moves) {
      if (legalMove.equals(move))
        return true;
    }
    return false;
  }
}
