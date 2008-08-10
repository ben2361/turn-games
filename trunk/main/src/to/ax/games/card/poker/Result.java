package to.ax.games.card.poker;

import java.util.EnumSet;
import java.util.List;
import java.util.Vector;

import to.ax.games.card.PlayingCard;

public class Result {
  public enum Category {
    STRAIGHT_FLUSH, FOUR_OF_A_KIND, FULL_HOUSE, FLUSH, STRAIGHT, THREE_OF_A_KIND, TWO_PAIR, PAIR,
    NOTHING;
  }
  
  public Category category() { return category; }
  public List<EnumSet<PlayingCard>> scoredHand() { return scoredHand; }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) 
      return true;
    if (!(obj instanceof Result))
      return false;
    Result that = (Result) obj;
    return that.category.equals(this.category) && that.scoredHand.equals(this.scoredHand);
  }
  
  @Override
  public int hashCode() {
    return 31 * category.hashCode() + scoredHand.hashCode();
  }
  
  private Category category;
  private List<EnumSet<PlayingCard>> scoredHand;
  
  Result(Category category, EnumSet<PlayingCard> hand) {
    this.category = category;
    this.scoredHand = new Vector<EnumSet<PlayingCard>>();
    scoredHand.add(hand);
  }
  
  Result(Category category, EnumSet<PlayingCard> part, EnumSet<PlayingCard> part2) {
    // We can't do this all in one with ... because of generic issues involving arrays.
    this(category, part);
    scoredHand.add(part2);
  }
  
  Result(Category category, EnumSet<PlayingCard> part, EnumSet<PlayingCard> part2, 
      EnumSet<PlayingCard> part3) {
    this(category, part, part2);
    scoredHand.add(part3);
  }
}
