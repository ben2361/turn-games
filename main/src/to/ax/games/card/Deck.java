package to.ax.games.card;

import java.util.EnumSet;
import java.util.Set;

/**
 * @author Tom Ritchford (tom@swirly.com)
 *
 */
public class Deck<Card extends Enum<Card>> {
  public Deck(EnumSet<Card> cards) {
    this.cards = cards.clone();
  }
  
  private final Set<Card> cards;
  
  protected Card deal(RandomIntegerGenerator randomIntegerGenerator) {
    final int size = cards.size();
    if (size == 0)
      return null;
    // Select a position in the deck and take that card.
    int position = randomIntegerGenerator.randomInteger(size);
    for (Card c: cards) {
      if (0 == position) {
        cards.remove(c);
        return c;
      }
      --position;
    }
    throw new AssertionError("Didn't find any cards!");
  }
  
  public int size() { return cards.size(); }
}
