package to.ax.games.checkers.rules;

import java.util.List;

import to.ax.games.Rules;
import to.ax.games.checkers.Board;
import to.ax.games.checkers.Game;
import to.ax.games.checkers.Move;
import to.ax.games.checkers.Piece;
import to.ax.games.checkers.Square;
import to.ax.games.util.IsMoveValid;

/**
 * @author Tom Ritchford (tom@swirly.com)
 *
 */
public class CheckersRules implements Rules<Game, Move, String> {
  public Game applyMove(Game game, Move move) {
    return null;
  }
  public Game getInitialGameState() {
    return null;
  }
  public List<Move> getLegalMoves(Game game) {
    return null;
  }
  public String getResult(Game game) {
    return null;
  }
  
  public boolean isMoveLegal(Game gameState, Move move) {
    return IsMoveValid.isMoveValid(gameState, move, this);
  }
  
  public static Board getInitialBoard(Variant variant) {
    Piece[][] pieces = new Piece[variant.width][];
    return new Board(variant, pieces);
  }
  
  public static int[] getFileRank(Variant variant, Square square) {
    int index = square.ordinal();
    int rank = index / variant.width;
    if (rank < 0 || rank >= variant.height)
      return null;
    int file = index % variant.width;
    return new int[] { file, rank };
  }


  public static Piece getPiece(Variant variant, Piece pieces[][], Square square) {
    if (square != null) {
      final int[] fileRank = getFileRank(variant, square);
      if (fileRank != null) {
        return pieces[fileRank[0]][fileRank[1]];
      }
    }
    return null;
  }

  public static void setPiece(Variant variant, Piece pieces[][], Square square, Piece piece) {
    // No checking here -- this must ALWAYS be a valid square or throw an exception.
    int[] fileRank = getFileRank(variant, square);
    assert fileRank != null;
    pieces[fileRank[0]][fileRank[1]] = piece;
  }
  
  public static Square getSquare(Variant variant, int file, int rank) {
    if (file >= 0 && file < variant.height / 2 && rank >= 0 && rank < variant.width)
      return Square.values()[measure(variant, file, rank)];
    else  
      return null;
  }
  
  public static Square getSquare(Variant variant, int[] offset) { 
    return getSquare(variant, offset[0], offset[1]); 
  }

  public static int measure(Variant variant, int[] offset) { return measure(variant, offset[0], offset[1]);  }
  public static int measure(Variant variant, int file, int rank) { return file + variant.width * rank;  }

  // Move these to rules.
  public static Square next(Square square, int difference) {
    int i = square.ordinal() + difference;
    return (i >= 0 && i < Square.values().length) ? Square.values()[i] : null;
  }
}
