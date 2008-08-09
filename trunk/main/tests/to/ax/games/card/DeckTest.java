package to.ax.games.card;

import java.util.EnumSet;

import junit.framework.TestCase;

/**
 * @author Tom Ritchford (tom@swirly.com)
 *
 */
public class DeckTest extends TestCase {
  public void testDeal() {
    Deck<PlayingCard> deck = new Deck<PlayingCard>(EnumSet.allOf(PlayingCard.class));
    RandomIntegerGenerator first = new RandomIntegerGenerator() {
      public int randomInteger(int max) {
        return 0;
      }
    };

    for (int i = 0; i < PlayingCard.DECK_SIZE; ++i) {
      PlayingCard card = deck.deal(first);
      assertEquals(PlayingCard.values()[i], card);
    }
    assertEquals(deck.deal(first), null);
  
    RandomIntegerGenerator last = new RandomIntegerGenerator() {
      public int randomInteger(int max) {
        return max - 1;
      }
    };

    deck = new Deck<PlayingCard>(EnumSet.allOf(PlayingCard.class));
    for (int i = PlayingCard.DECK_SIZE - 1; i >= 0; --i) {
      PlayingCard card = deck.deal(last);
      assertEquals(PlayingCard.values()[i], card);
    }
    assertEquals(deck.deal(last), null);

  }
}
