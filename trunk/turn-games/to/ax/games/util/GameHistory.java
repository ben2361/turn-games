package to.ax.games.util;

import java.util.EnumSet;

import to.ax.games.chess.Board;
import to.ax.games.chess.Square;
import to.ax.games.chess.Game;

/**
 * @author Tom Ritchford (tom@swirly.com)
 *
 */
public class GameHistory extends Game {
  public GameHistory(Board board, int moveCount, int movesSincePawnOrCapture, 
      EnumSet<Square> castleSquares, Square epSquare) {
    super(board, moveCount, movesSincePawnOrCapture, castleSquares, epSquare);
  }
   
  /*
  private GameHistory(GameHistory parent, Move move, Game game, 
      Map<Board, Integer> boardCount) {
    this.parent = parent;
    this.move = move;
    this.game = game;
    this.boardCount = boardCount;
  }
  
  public int getBoardCount(Board board) {
    //boardCount.
    return 0;
  }
  
  
  private final GameHistory parent;
  private final Move move;
  private final Game game;
  private final Map<Board, Integer> boardCount;
  */
}
