package to.ax.games.card.poker;

import java.util.EnumSet;

import to.ax.games.card.PlayingCard;
import to.ax.games.card.Suit;

/**
 * @author Tom Ritchford (tom@swirly.com)
 *
 */
public class Flush implements HandFeature {
  public EnumSet<PlayingCard> getFeature(EnumSet<PlayingCard> hand) {
    EnumSet<PlayingCard> bestFlush = EnumSet.noneOf(PlayingCard.class);
    for (Suit suit: Suit.values()) {
      EnumSet<PlayingCard> oneSuit = suit.cards();
      oneSuit.retainAll(hand);
      if (oneSuit.size() > bestFlush.size())
        bestFlush = oneSuit;
    }
    return bestFlush;
  }

  private Flush() {}
  public static final Flush INSTANCE = new Flush();
}
