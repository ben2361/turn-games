package to.ax.games.util;

import java.util.Iterator;


/**
 * @author Tom Ritchford (tom@swirly.com)
 * TODO(tom): unit tests.
 */
public class FilteredIterator<Item> extends LookaheadIterator<Item> {
  public FilteredIterator(Filter<Item> filter, Iterator<Item> iterator) {
    this.filter = filter;
    this.iterator = iterator;
    setNext();
  }
  private final Filter<Item> filter;
  private final Iterator<Item> iterator;

  @Override
  protected Item findNext() {
    while (iterator.hasNext()) {
      Item nextItem = iterator.next();
      if (filter.accepts(nextItem))
        return nextItem;
    }
    return null;
  }
  
}
