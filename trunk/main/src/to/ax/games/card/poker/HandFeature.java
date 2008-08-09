package to.ax.games.card.poker;

import java.util.EnumMap;
import java.util.EnumSet;

import to.ax.games.card.PlayingCard;
import to.ax.games.card.PlayingCard.Rank;
import to.ax.games.card.PlayingCard.Suit;

public enum HandFeature {
  FLUSH(5) {    
    @Override
    public EnumSet<PlayingCard> getFeature(EnumSet<PlayingCard> hand) {
      EnumMap<Suit, EnumSet<PlayingCard>> suitCards = new EnumMap<Suit, EnumSet<PlayingCard>>(Suit.class);
      for (PlayingCard card: hand) {        
        EnumSet<PlayingCard> cardsInSuit = suitCards.get(card.suit());
        if (cardsInSuit == null) {
          suitCards.put(card.suit(), EnumSet.of(card));
        } else {
          cardsInSuit.add(card);
          if (cardsInSuit.size() >= size)
            return cardsInSuit;
        }
      }
      return null;
    }
  },

  STRAIGHT(5) {
    @Override
    public EnumSet<PlayingCard> getFeature(EnumSet<PlayingCard> hand) {
      EnumSet<PlayingCard> results = EnumSet.noneOf(PlayingCard.class);
      PlayingCard.Rank lastRank = null;
      for (PlayingCard card: hand) {
        final Rank rank = card.rank();
        if (rank.previous() == lastRank) { 
          results.add(card);
          if (results.size() >= size) {
            assert results.size() == size;
            return results;
          }
        } else if (rank != lastRank) {
          results = EnumSet.of(card);
        }
        lastRank = rank;
      }

      // Special rule for 1,2,3,4,5 straight.
      if (results.size() + 1 == size && lastRank == Rank.TWO) {
        for (PlayingCard card: hand) {
          if (card.rank() != Rank.ACE) 
            return null;
          results.add(card);
          return results;
        }
      }      
      return null;
    }
  }, 
  
  FOUR_OF_A_KIND(4),
  THREE_OF_A_KIND(3), 
  PAIR(2), 

  ;
  
  HandFeature(int size) { this.size = size; }
  final int size;

  public EnumSet<PlayingCard> getFeature(EnumSet<PlayingCard> hand) {
    // TODO(tom):  how to make this code generic between Rank and Suit?!  The code is identical.
    EnumMap<Rank, EnumSet<PlayingCard>> rankCards = new EnumMap<Rank, EnumSet<PlayingCard>>(Rank.class);
    for (PlayingCard card: hand) {
      
      EnumSet<PlayingCard> cardsInRank = rankCards.get(card.rank());
      if (cardsInRank == null) {
        rankCards.put(card.rank(), EnumSet.of(card));
      } else {
        cardsInRank.add(card);
        if (cardsInRank.size() >= size)
          return cardsInRank;
      }
    }
    return null;
  }
}