package to.ax.games.util;

/**
 * @author Tom Ritchford (tom@swirly.com)
 *
 */
public interface Filter<Item> {
  boolean accepts(Item item);
}
