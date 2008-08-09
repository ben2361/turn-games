package to.ax.games.util;


/**
 * @author Tom Ritchford (tom@swirly.com)
 * TODO(tom): test this and test toString() and hashCode() on derived classes too.
 */
abstract public class CachedHash<C> {
  public String toString() { 
    computeHashAndToString();
    return toString;
  }

  @Override
  public int hashCode() {
    computeHashAndToString();
    return hashCode;
  }
  
  protected void clearHashCode() {
    toString = null;
  }
  
  private String toString = null;
  private int hashCode = 0;
  
  private void computeHashAndToString() {
    if (toString == null) {
      toString = computeToString(); 
      hashCode = toString.hashCode();
    }
  }

  abstract protected String computeToString();
  
  @SuppressWarnings("unchecked")
  @Override
  public final boolean equals(Object that) {
    return (that == this) || 
        ((that != null) && getClass().equals(that.getClass()) && computeEquals((C) that));
  }

  protected boolean computeEquals(C that) {
    computeHashAndToString();
    return hashCode == that.hashCode() && toString.equals(that.toString());
  }
}
