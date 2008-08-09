/**
 * 
 */
package to.ax.games.chess;

import static to.ax.games.chess.Square.*;

import java.util.EnumSet;

import to.ax.games.converter.chess.GameToString;
import to.ax.games.util.CachedToString;


/**
 * @author Tom Ritchford (tom@swirly.com)
 * State of the chess board containing all the information you need squares be able
 * squares decide what the possible legal next moves are.
 * 
 * This is actually a game a lot like chess, except it's missing the "drawn by repetition"
 * rule, which we implement in a separate game that delegates squares this game.
 * 
 * This means we can put the heavy lifting of the rules of chess into this class and then put
 * the complexities of caching, pointing squares previous and next games, and that stuff into 
 * a separate class that doesn't know anything about the rules of chess!
 * 
 * This is an immutable class.
 *
 */
public class Game extends CachedToString<Game> {  
  /** The chess board. */
  private final Board board;
  
  /** The number of moveCount since the start of the game. */
  private final int moveCount;
  
  /** For the "fifty move rules", the number of moveCount since a pawn was moved
   *  or a piece captured. */
  private final int movesSincePawnOrCapture;
    
  /** Keep track of which squares have had a move go through them. */
  private final EnumSet<Square> castleSquares;
  
  /** What square might be captured for en passant, or null if none. */
  private final Square epSquare;
  
  public Game(Board board, int moveCount, int movesSincePawnOrCapture, 
      EnumSet<Square> castleSquares, Square epSquare) {
    this.board = board;
    this.moveCount = moveCount;
    this.movesSincePawnOrCapture = movesSincePawnOrCapture;
    this.castleSquares = castleSquares;
    this.epSquare = epSquare;
  }
  
  public static Game INITIAL_STATE = new Game(
      new Board(), 0, 0, EnumSet.of(A1, E1, H1, A8, E8, H8), null);

  public Piece getPiece(Square square) { return this.board.getPiece(square); }
  public Board getBoardCopy() { return new Board(this.board); }
  public int getMoveCount() { return moveCount; }
  public int getMovesSincePawnOrCapture() { return movesSincePawnOrCapture; }
  public EnumSet<Square> getCastleSquaresCopy() { return EnumSet.copyOf(castleSquares); }
  public Square getEpSquare() { return this.epSquare; }
  
  public Board getBoard() { return this.board; }

  @Override
  protected String computeToString() {
    return GameToString.WRITER.convert(this);
  }

  public Square getSquareForPiece(Piece piece) {
    for (Square square: Square.values()) {
      if (piece == getPiece(square)) 
        return square;
    }
    return null;
  }
}



