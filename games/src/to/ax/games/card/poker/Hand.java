/**
 * @author Tom Ritchford (tom@swirly.com)
 *
 */
package to.ax.games.card.poker;

import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Vector;

import to.ax.games.card.PlayingCard;

public enum Hand {
  STRAIGHT_FLUSH(HandFeature.STRAIGHT, HandFeature.FLUSH) {
    @Override
    public boolean matchesHand(
        EnumSet<PlayingCard> cards, List<EnumSet<PlayingCard>> scoredHand) {
      for (HandFeature feature: features) {
        if (feature.getFeature(cards) == null) 
          return false;
      }
      scoredHand.clear();
      scoredHand.add(cards);
      return true;
    }
  },
  FOUR_OF_A_KIND(HandFeature.FOUR_OF_A_KIND), 
  FULL_HOUSE(HandFeature.THREE_OF_A_KIND, HandFeature.PAIR),   
  FLUSH(HandFeature.FLUSH), 
  STRAIGHT(HandFeature.STRAIGHT),
  THREE_OF_A_KIND(HandFeature.THREE_OF_A_KIND),
  TWO_PAIR(HandFeature.PAIR, HandFeature.PAIR),
  PAIR(HandFeature.PAIR),
  NOTHING,
  ;

  static public class ScoredHand {
    public ScoredHand(Hand hand, List<EnumSet<PlayingCard>> scoredHand) {
      this.hand = hand;
      this.scoredHand = scoredHand;
    }
    private Hand hand;
    private List<EnumSet<PlayingCard>> scoredHand;
    public Hand hand() { return hand; }
    public List<EnumSet<PlayingCard>> scoredHand() { return scoredHand; }
  }
  
  static public ScoredHand score(EnumSet<PlayingCard> cards) {
    List<EnumSet<PlayingCard>> scoredHand = new Vector<EnumSet<PlayingCard>>();
    for (Hand h: values()) {
      if (h.matchesHand(cards, scoredHand)) 
        return new ScoredHand(h, scoredHand);
    }
    assert false;
    return null;
  }
  
  public boolean matchesHand(
      EnumSet<PlayingCard> cards, List<EnumSet<PlayingCard>> scoredHand) {
    scoredHand.clear();
    EnumSet<PlayingCard> remainingHand = EnumSet.copyOf(cards);
    for (HandFeature feature: features) {
      EnumSet<PlayingCard> matchedFeature = feature.getFeature(remainingHand);
      if (matchedFeature == null) 
        return false;
      scoredHand.add(matchedFeature);
      boolean featureRemoved = remainingHand.removeAll(matchedFeature);
      assert featureRemoved;
    }
    if (remainingHand.size() > 0)
      scoredHand.add(remainingHand);
    return true;
  }
  
  Hand()                               { features = new Vector<HandFeature>(); }
  Hand(HandFeature f)                  { this(); features.add(f); }
  Hand(HandFeature f1, HandFeature f2) { this(f1); features.add(f2); }
  
  protected final Collection<HandFeature> features;
}