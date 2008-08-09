package to.ax.games.card.poker;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import to.ax.games.card.PlayingCard;
import to.ax.games.card.poker.Hand.ScoredHand;
import static to.ax.games.card.poker.Hand.*;
import static to.ax.games.card.PlayingCard.*;

/**
 * @author Tom Ritchford (tom@swirly.com)
 *
 */
@SuppressWarnings("unchecked")  // Why?!
public class HandTest extends TestCase {
  public void doTestHands(
      Hand hand, EnumSet<PlayingCard> cards, EnumSet<PlayingCard> ...expectedFeatures) {
    final ScoredHand scoredCards = Hand.score(cards);
    assertEquals(hand, scoredCards.hand());
    final List<EnumSet<PlayingCard>> scoredHand = scoredCards.scoredHand();
    Iterator<EnumSet<PlayingCard>> actualFeaturesIterator = scoredHand.iterator();
    for (EnumSet<PlayingCard> expectedFeature : expectedFeatures) {
      assertTrue(actualFeaturesIterator.hasNext());
      assertEquals(expectedFeature, actualFeaturesIterator.next());
    }
    assertFalse(actualFeaturesIterator.hasNext());
  }
  
  public void doTestHands(Hand hand, PlayingCard ... cards) {
    EnumSet<PlayingCard> cardSet = EnumSet.noneOf(PlayingCard.class);
    for (PlayingCard card: cards)
      cardSet.add(card);

    doTestHands(hand, cardSet, cardSet);
  }
  
  @Test 
  public void testNothing() {
    doTestHands(NOTHING, _2C, _3C);
    doTestHands(NOTHING, _2C, _3C, _4D, _5D, _7D);
    doTestHands(NOTHING, _2S, _3S, _4S, _AS, _KC);  // No wrap-around straights.
  }
  
  @Test 
  public void testPair() {
    doTestHands(PAIR, _2C, _2S);
    doTestHands(PAIR, EnumSet.of(_2C, _2S, _4D, _5D, _7D),
        EnumSet.of(_2C, _2S), EnumSet.of(_4D, _5D, _7D));
    doTestHands(PAIR, EnumSet.of(_6C, _6S, _4D, _5D, _7D),
        EnumSet.of(_6C, _6S), EnumSet.of(_4D, _5D, _7D));
  }
  
  @Test 
  public void testTwoPair() {
    doTestHands(TWO_PAIR, EnumSet.of(_2C, _2S, _4D, _4S, _7D),
        EnumSet.of(_4D, _4S), EnumSet.of(_2C, _2S), EnumSet.of(_7D));
    doTestHands(TWO_PAIR, EnumSet.of(_2C, _2S, _4D, _4S),
        EnumSet.of(_4D, _4S), EnumSet.of(_2C, _2S));
  }
  
  @Test 
  public void testThree() {
    doTestHands(THREE_OF_A_KIND, _2C, _2S, _2H);
    doTestHands(THREE_OF_A_KIND, EnumSet.of(_2C, _2S, _2H, _4S, _7D),
        EnumSet.of(_2C, _2S, _2H), EnumSet.of(_4S, _7D));
  }
  
  @Test 
  public void testStraight() {
    doTestHands(STRAIGHT, _AH, _2S, _3S, _4S, _5S);
    doTestHands(STRAIGHT, _2S, _3S, _4H, _5S, _6S);
    doTestHands(STRAIGHT, _9S, _TS, _JH, _QS, _KS);
    doTestHands(STRAIGHT, _TD, _JC, _QH, _KS, _AH);
  }
  
  @Test 
  public void testFlush() {
    doTestHands(FLUSH, _KH, _AH, _2H, _3H, _4H);
    doTestHands(FLUSH, _8S, _AS, _TS, _3S, _7S);
  }
  
  @Test 
  public void testFullHouse() {
    doTestHands(FULL_HOUSE, EnumSet.of(_AH, _AS, _KS, _KD, _KC),
        EnumSet.of(_KS, _KD, _KC), EnumSet.of(_AH, _AS));
  }
  
  @Test 
  public void testStraightFlushes() {
    doTestHands(STRAIGHT_FLUSH, _AS, _2S, _3S, _4S, _5S);
    doTestHands(STRAIGHT_FLUSH, _2S, _3S, _4S, _5S, _6S);
    doTestHands(STRAIGHT_FLUSH, _9S, _TS, _JS, _QS, _KS);
    doTestHands(STRAIGHT_FLUSH, _TS, _JS, _QS, _KS, _AS);
  }
}
