package to.ax.games.util;

public enum Color { 
  BLACK, WHITE;
  public Color not() { return values()[1 - ordinal()]; }
}