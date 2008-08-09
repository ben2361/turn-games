package to.ax.games.chess.rules;


/**
 * @author Tom Ritchford (tom@swirly.com)
 *
 */
public class KnightRules extends PieceRules {
  public int[][] getMoveOffsets() { return MOVES; }  
  public static final int[][] MOVES = {
              {-1, -2},         {1, -2},
    {-2, -1},                              {2, -1},
    
    {-2,  1},                              {2,  1},
              {-1,  2},         {1,  2},
  };

  @Override
  public boolean isPieceBetween(GameMove gameMove) {
    return false;
  }
}
