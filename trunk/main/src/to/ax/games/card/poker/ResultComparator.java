package to.ax.games.card.poker;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.Iterator;

import to.ax.games.card.PlayingCard;

/**
 * @author Tom Ritchford (tom@swirly.com)
 * Compare two analyzed hands to see which is better than the other.
 */
public class ResultComparator implements Comparator<Result> {
  public int compare(Result that, Result newThis) {
    int result = newThis.category().compareTo(that.category());
    if (result != 0)
      return result;
    
    assert newThis.scoredHand().size() == that.scoredHand().size();
    Iterator<EnumSet<PlayingCard>> thisFeatureIterator = newThis.scoredHand().iterator();
    for (EnumSet<PlayingCard> thatElement : that.scoredHand()) {
      Iterator<PlayingCard> thisCardIterator = thisFeatureIterator.next().iterator();
      for (PlayingCard thatCard: thatElement) {
        result = thisCardIterator.next().rank().compareTo(thatCard.rank());
        if (result != 0)
          return result;
      }
    }

    assert result == 0;
    return result;
  }
}
