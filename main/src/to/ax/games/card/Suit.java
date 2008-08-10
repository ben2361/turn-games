package to.ax.games.card;

import java.util.EnumSet;


public enum Suit {
  SPADE(Color.BLACK), HEART(Color.RED), CLUB(Color.BLACK), DIAMOND(Color.RED), 
  ;

  public enum Color { RED, BLACK; }
  public Color color() { return color; }
  
  /** @return the playing card of this suit and the given rank. */
  public PlayingCard of(Rank rank) {
    return PlayingCard.values()[ordinal() + 4 * rank.ordinal()];
  }
  
  /** @return all PlayingCard instances with this rank. */
  public EnumSet<PlayingCard> cards() {    
    if (cards == null) {
      cards = EnumSet.noneOf(PlayingCard.class);
      for (Rank rank: Rank.values())
        cards.add(of(rank));
    }
    return cards.clone(); 
  }

  Suit(Color color) { 
    this.color = color;
  }
  
  private EnumSet<PlayingCard> cards;
  private final Color color;    
}