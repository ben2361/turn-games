package to.ax.games.card.poker;

import static to.ax.games.card.poker.Result.Category.*;

import java.util.EnumSet;

import to.ax.games.card.PlayingCard;

/**
 * @author Tom Ritchford (tom@swirly.com)
 * Score a poker hand by returning a result.
 */
public abstract class Scorer {
  static public Result score(EnumSet<PlayingCard> cards) {
    EnumSet<PlayingCard> bestFlush = Flush.INSTANCE.getFeature(cards);
    EnumSet<PlayingCard> bestStraight = Straight.INSTANCE.getFeature(cards);
    EnumSet<PlayingCard> bestPair = Multiple.INSTANCE.getFeature(cards);
    if (bestFlush.size() == 5) {
      if (bestStraight.equals(bestFlush))
        return new Result(STRAIGHT_FLUSH, bestFlush);
      else   
        return new Result(FLUSH, bestFlush);
    }
    
    if (bestStraight.size() == 5) 
      return new Result(STRAIGHT, bestStraight);
    
    int size = bestPair.size();
    EnumSet<PlayingCard> remainingCards = cards.clone();
    if (size < 2)
      return new Result(NOTHING, remainingCards);
    if (bestPair.size() == 4) 
      return new Result(FOUR_OF_A_KIND, bestPair, remainingCards);
    
    remainingCards.removeAll(bestPair);
    EnumSet<PlayingCard> secondBestPair = Multiple.INSTANCE.getFeature(remainingCards);
    boolean extraPair = secondBestPair.size() == 2;
    if (extraPair)
      remainingCards.removeAll(secondBestPair);
    
    if (bestPair.size() == 3) {
      if (extraPair)
        return new Result(FULL_HOUSE, bestPair, secondBestPair);
      else
        return new Result(THREE_OF_A_KIND, bestPair, remainingCards);
    }
    if (extraPair)
      return new Result(TWO_PAIR, bestPair, secondBestPair, remainingCards);
    else
      return new Result(PAIR, bestPair, remainingCards);
  }
}
