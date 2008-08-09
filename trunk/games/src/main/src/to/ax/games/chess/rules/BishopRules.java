package to.ax.games.chess.rules;

/**
 * @author Tom Ritchford (tom@swirly.com)
 * Tested by larger tests.
 */
public class BishopRules extends PieceRules {
  @Override
  public int[][] getMoveOffsets() { return MOVES; }  
  public static final int[][] MOVES = {
    {-7, -7},                                                                                                                           {7, -7}, 
              {-6, -6},                                                                                                        {6, -6},
                        {-5, -5},                                                                                      {5, -5},
                                  {-4, -4},                                                                   {4, -4},
                                           {-3,  -3},                                                {3, -3},
                                                      {-2,  -2},                            {2, -2},
                                                                {-1, -1},          {1, -1},

                                                                {-1,  1},          {1,  1},
                                                      {-2,   2},                            {2,  2},
                                           {-3,   3},                                                {3,  3},
                                  {-4,  4},                                                                   {4,  4},
                        {-5,  5},                                                                                      {5,  5},
              {-6,  6},                                                                                                        {6,  6},
    {-7,  7},                                                                                                                           {7,  7}, 
  };  
}
