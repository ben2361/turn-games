package to.ax.games.chess.rules;

/**
 * @author Tom Ritchford (tom@swirly.com)
 *
 */
public class RookRules extends PieceRules {
  @Override
  public int[][] getMoveOffsets() { return MOVES;  }
  private static final int[][] MOVES = {
                                                                        {0, -7}, 
                                                                        {0, -6}, 
                                                                        {0, -5}, 
                                                                        {0, -4}, 
                                                                        {0, -3}, 
                                                                        {0, -2}, 
                                                                        {0, -1}, 
  {-7,  0}, {-6,  0}, {-5,  0}, {-4,  0}, {-3,  0}, {-2,  0}, {-1,  0},           {1,  0}, {2,  0}, {3,  0}, {4,  0}, {5,  0}, {6,  0}, {7,  0},
                                                                        {0,  1}, 
                                                                        {0,  2}, 
                                                                        {0,  3}, 
                                                                        {0,  4}, 
                                                                        {0,  5}, 
                                                                        {0,  6}, 
                                                                        {0,  7}, 
  };
}
