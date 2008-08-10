package to.ax.games.card;

import java.util.EnumSet;

public enum Rank { 
  ACE { @Override public Rank previous()     { return TWO; } },
  KING, QUEEN, JACK, TEN, NINE, EIGHT, SEVEN, SIX, FIVE, FOUR, THREE,  
  TWO { @Override public Rank next() { return ACE; } }, 
  ;

  public Rank next()     { return values()[ordinal() + 1]; }
  public Rank previous() { return values()[ordinal() - 1]; }
  public PlayingCard of(Suit suit) { return suit.of(this); }
  
  /** @return all PlayingCard instances with this rank. */
  public EnumSet<PlayingCard> cards() {
    if (cards == null) {
      PlayingCard[] values = PlayingCard.values();
      int spade = ordinal() * 4;  // aka Suit.values().length;
      cards = EnumSet.range(values[spade], values[spade + 3]);
    }
    return cards.clone();
  }
  private EnumSet<PlayingCard> cards;
}