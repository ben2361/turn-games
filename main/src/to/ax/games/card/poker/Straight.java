package to.ax.games.card.poker;

import java.util.EnumSet;

import to.ax.games.card.PlayingCard;
import to.ax.games.card.Rank;

/**
 * @author Tom Ritchford (tom@swirly.com)
 *
 */
public class Straight implements HandFeature {
  public EnumSet<PlayingCard> getFeature(EnumSet<PlayingCard> hand) {
    EnumSet<PlayingCard> currentStraight = EnumSet.noneOf(PlayingCard.class);
    EnumSet<PlayingCard> bestStraight = currentStraight;
    Rank lastRank = null;
    for (PlayingCard card: hand) {
      final Rank rank = card.rank();
      if (rank.previous() == lastRank) { 
        currentStraight.add(card);
        if (currentStraight.size() > bestStraight.size())
          bestStraight = currentStraight;
      } else if (rank != lastRank) {
        currentStraight = EnumSet.of(card);
      }
      lastRank = rank;
    }

    // 1,2,3,4,5 straight special rules.
    if (lastRank == Rank.TWO && currentStraight == bestStraight) {
      PlayingCard possibleAce = hand.iterator().next();
      if (possibleAce.rank() == Rank.ACE)
          bestStraight.add(possibleAce);
    }
    return bestStraight;
  }

  private Straight() {}
  public static final Straight INSTANCE = new Straight();
}
