package to.ax.games.util;

import java.util.Iterator;

/**
 * @author Tom Ritchford (tom@swirly.com)
 *
 * @param <E>
 */
abstract public class LookaheadIterator<E> implements Iterator<E>, Iterable<E>, Cloneable {
  public boolean hasNext() { return nextItem != null; }
  public void remove() { throw new UnsupportedOperationException(); }
  public E next() {
    if (!hasNext())
       return null;
    E result = nextItem;
    setNext();
    return result;
  }

  @SuppressWarnings("unchecked")
  @Override
  public LookaheadIterator<E> clone() {    
    try {
      return (LookaheadIterator<E>) super.clone();
    } catch (Exception e) {
      // Should never happen.
      e.printStackTrace();
      return null;
    }
  }
  
  public Iterator<E> iterator() {
    return clone();
  }
  
  abstract protected E findNext();
  protected final void setNext() {
    nextItem = findNext();
  }
  private E nextItem;
  
  
}