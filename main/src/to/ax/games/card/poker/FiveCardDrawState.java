package to.ax.games.card.poker;

import java.util.EnumSet;
import java.util.List;
import java.util.Vector;

import to.ax.games.card.Deck;
import to.ax.games.card.PlayingCard;
import to.ax.games.card.RandomIntegerGenerator;

/**
 * @author Tom Ritchford (tom@swirly.com)
 * This represents the state of a game of five-card draw poker.
 */
public class FiveCardDrawState<Player> {
  public FiveCardDrawState(RandomIntegerGenerator generator, int playerCount) {
    this.generator = generator;
    this.hands = new Vector<EnumSet<PlayingCard>>(playerCount);
    for (int card = 0; card < 5; ++card) {
      for (int player = 0; player < playerCount; ++player)
        deal(player);
    }
    alreadyMoved = new boolean[playerCount];
  }
  
  private void deal(int playerIndex) {
    hands.get(playerIndex).add(deck.deal(generator));
  }
  
  public static class Move {
    final int playerIndex;
    final EnumSet<PlayingCard> discards;
    public Move(int playerIndex, EnumSet<PlayingCard> discards) {
      this.playerIndex = playerIndex;
      this.discards = discards;
    }
  }

  public boolean isValidMove(Move move) {
    return !alreadyMoved[move.playerIndex] && 
        hands.get(move.playerIndex).containsAll(move.discards);
  }
  
  private final RandomIntegerGenerator generator;
  private List<EnumSet<PlayingCard>> hands;
  private final Deck<PlayingCard> deck = new Deck<PlayingCard>(EnumSet.allOf(PlayingCard.class));
  private final boolean[] alreadyMoved;
}
