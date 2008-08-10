package to.ax.games.card.poker;

import java.util.EnumSet;

import to.ax.games.card.PlayingCard;

/**
 * @author Tom Ritchford (tom@swirly.com)
 * Pairs, three and four of a kind.
 */
public class Multiple implements HandFeature {
  public EnumSet<PlayingCard> getFeature(EnumSet<PlayingCard> hand) {
    hand = hand.clone();
    EnumSet<PlayingCard> bestPair = EnumSet.noneOf(PlayingCard.class);
    while (hand.size() > 0) {
      EnumSet<PlayingCard> pair = hand.iterator().next().rank().cards();
      pair.retainAll(hand);
      if (pair.size() > bestPair.size())
        bestPair = pair;
      hand.removeAll(pair);
    }
    return bestPair;
  }

  private Multiple() {}
  public static final Multiple INSTANCE = new Multiple();
}
