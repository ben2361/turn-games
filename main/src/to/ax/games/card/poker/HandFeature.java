package to.ax.games.card.poker;

import java.util.EnumSet;

import to.ax.games.card.PlayingCard;

/**
 * @author Tom Ritchford (tom@swirly.com)
 * An interface for objects that can extract a feature like a pair or straight from a hand of 
 * cards. We never actually use these items as an interface but we might as well have it for 
 * neatness.
 */
public interface HandFeature {
  EnumSet<PlayingCard> getFeature(EnumSet<PlayingCard> hand);
}