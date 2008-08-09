package to.ax.games.util;


/**
 * Many of the classes involved here have a unique toString() that completely
 * identifies the item and can be used for sorting and equality and generating
 * hash keys... but is expensive to compute for routine operations.
 * 
 * CachedToString is an abstract base class that caches an expensive toString()
 * as well as its derived properties.  Your derived class needs to implement
 * the abstract method computeToString().
 * 
 * @author Tom Ritchford (tom@swirly.com)
 * TODO(tom): This code is tested indirectly through the tests on the chess
 * code. Directly test this and test toString() and hashCode() on derived 
 * classes too.
 */
abstract public class CachedToString<C> implements Comparable<CachedToString<C>> {
  /** The cached toString description.  If null, this means it needs to be 
   * recomputed.*/
  private String toString;
  
  /** The cached hash code.  If 0, this means it needs to be 
   * recomputed.*/
  private int hashCode;

  /** Mark toString and hashCode as needing to be recomputed. */
  protected void invalidateToString() {
    toString = null;
    hashCode = 0;
  }
  
  protected CachedToString() {
    invalidateToString();
  }
  
  /** @return a string representation of this object.  This representation 
   * must be compatible with equals, so that two objects are equals() exactly
   * if they have the same value returned from computeToString().
   * 
   * The implementation caches the value returned from this call, which you can 
   * invalidate by calling invalidateToString(). */
  abstract protected String computeToString();

  // Finally, we have the code that lets us be a good Java Object.
  @Override
  public String toString() { 
    if (toString == null) 
      toString = computeToString(); 
    return toString;
  }

  @Override
  public int hashCode() {    
    if (hashCode == 0) 
      hashCode = toString().hashCode();
    return hashCode;
  }

  public int compareTo(CachedToString<C> that) {
    if (!getClass().equals(that.getClass())) {
      throw new IllegalArgumentException(
          "Expected class " + getClass() + ", got " + that.getClass());
    }
    return toString().compareTo(that.toString());
  }
  
  @Override
  public final boolean equals(Object that) {
    if (that == this) 
      return true;
    else if (that == null)
      return false;
    else if (!getClass().equals(that.getClass()))
      return false; 
    @SuppressWarnings("unchecked") C c = (C) that;
    return hashCode() == c.hashCode() && toString().equals(c.toString());
  }
}
