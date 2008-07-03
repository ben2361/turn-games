/**
 * 
 */
package to.ax.games.chess;

/**
 * @author Tom Ritchford (tom@swirly.com)
 *
 */
public enum Square {
  A8, B8, C8, D8, E8, F8, G8, H8,
  A7, B7, C7, D7, E7, F7, G7, H7,
  A6, B6, C6, D6, E6, F6, G6, H6,
  A5, B5, C5, D5, E5, F5, G5, H5,
  A4, B4, C4, D4, E4, F4, G4, H4,
  A3, B3, C3, D3, E3, F3, G3, H3,
  A2, B2, C2, D2, E2, F2, G2, H2,
  A1, B1, C1, D1, E1, F1, G1, H1;

  public static final int WIDTH = 8;
  public static final int HEIGHT = 8;
  
  private Square() {
    int ord = ordinal();
    this.rank = ord / WIDTH;
    this.file = ord % WIDTH;
  }
  
  public int[] offset(Square square) {
    return new int[] { file - square.file, rank - square.rank };
  }
  
  public int minus(Square square) { return measure(offset(square)); }
  
  public static Square locate(int file, int rank) {
    if (file >= 0 && file < WIDTH && rank >= 0 && rank < HEIGHT)
      return values()[measure(file, rank)];
    else  
      return null;
  }
  
  public Square add(int[] offset) { return add(offset[0], offset[1]); }
  public Square add(int file, int rank) { 
    return locate(this.file + file, this.rank + rank); 
  }
  
  public Square next(int difference) {
    int i = ordinal() + difference;
    return (i >= 0 && i < values().length) ? values()[i] : null;
  }
  public Square next() { return next(1); }  
  
  public static int measure(int[] offset) { return measure(offset[0], offset[1]);  }
  public static int measure(int file, int rank) { return file + WIDTH * rank;  }
  
  public final int rank;
  public final int file;
}
