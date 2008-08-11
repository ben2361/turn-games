package to.ax.games.card.poker;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import to.ax.games.card.PlayingCard;
import to.ax.games.card.poker.Result.Category;
import static to.ax.games.card.poker.Result.Category.*;
import static to.ax.games.card.PlayingCard.*;

/**
 * @author Tom Ritchford (tom@swirly.com)
 *
 */
@SuppressWarnings("unchecked")  // doTestCategories breaks provable genericity, but this is fine.
public class HandTest extends TestCase {
  public void doTestCategories(
      Category category, EnumSet<PlayingCard> cards, EnumSet<PlayingCard> ...expectedFeatures) {
    final Result scoredCards = Scorer.score(cards);
    assertEquals(category, scoredCards.category());
    final List<EnumSet<PlayingCard>> scoredHand = scoredCards.scoredHand();
    Iterator<EnumSet<PlayingCard>> actualFeaturesIterator = scoredHand.iterator();
    for (EnumSet<PlayingCard> expectedFeature : expectedFeatures) {
      assertTrue(actualFeaturesIterator.hasNext());
      assertEquals(expectedFeature, actualFeaturesIterator.next());
    }
    assertFalse(actualFeaturesIterator.hasNext());
  }
  
  public void doTestCategories(Category category, PlayingCard ... cards) {
    EnumSet<PlayingCard> cardSet = EnumSet.noneOf(PlayingCard.class);
    for (PlayingCard card: cards)
      cardSet.add(card);

    doTestCategories(category, cardSet, cardSet);
  }
  
  @Test 
  public void testNothing() {
    doTestCategories(NOTHING, _2C, _3C, _4D, _5D, _7D);
    doTestCategories(NOTHING, _2S, _3S, _4S, _AS, _KC);  // No wrap-around straights.
  }
  
  @Test 
  public void testPair() {
    doTestCategories(PAIR, EnumSet.of(_2C, _2S, _4D, _5D, _7D),
        EnumSet.of(_2C, _2S), EnumSet.of(_4D, _5D, _7D));
    doTestCategories(PAIR, EnumSet.of(_6C, _6S, _4D, _5D, _7D),
        EnumSet.of(_6C, _6S), EnumSet.of(_4D, _5D, _7D));
  }
  
  @Test 
  public void testTwoPair() {
    doTestCategories(TWO_PAIR, EnumSet.of(_2C, _2S, _4D, _4S, _7D),
        EnumSet.of(_4D, _4S), EnumSet.of(_2C, _2S), EnumSet.of(_7D));
  }
  
  @Test 
  public void testThree() {
    doTestCategories(THREE_OF_A_KIND, EnumSet.of(_2C, _2S, _2H, _4S, _7D),
        EnumSet.of(_2C, _2S, _2H), EnumSet.of(_4S, _7D));
  }
  
  @Test 
  public void testStraight() {
    doTestCategories(STRAIGHT, _AH, _2S, _3S, _4S, _5S);
    doTestCategories(STRAIGHT, _2S, _3S, _4H, _5S, _6S);
    doTestCategories(STRAIGHT, _9S, _TS, _JH, _QS, _KS);
    doTestCategories(STRAIGHT, _TD, _JC, _QH, _KS, _AH);
  }
  
  @Test 
  public void testFlush() {
    doTestCategories(FLUSH, _KH, _AH, _2H, _3H, _4H);
    doTestCategories(FLUSH, _8S, _AS, _TS, _3S, _7S);
  }
  
  @Test 
  public void testFullHouse() {
    doTestCategories(FULL_HOUSE, EnumSet.of(_AH, _AS, _KS, _KD, _KC),
        EnumSet.of(_KS, _KD, _KC), EnumSet.of(_AH, _AS));
  }
  
  @Test 
  public void testStraightFlushes() {
    doTestCategories(STRAIGHT_FLUSH, _AS, _2S, _3S, _4S, _5S);
    doTestCategories(STRAIGHT_FLUSH, _2S, _3S, _4S, _5S, _6S);
    doTestCategories(STRAIGHT_FLUSH, _9S, _TS, _JS, _QS, _KS);
    doTestCategories(STRAIGHT_FLUSH, _TS, _JS, _QS, _KS, _AS);
  }
}
