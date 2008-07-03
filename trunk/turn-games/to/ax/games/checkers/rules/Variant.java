package to.ax.games.checkers.rules;

import to.ax.games.util.Color;
import static to.ax.games.util.Color.*;
import static to.ax.games.checkers.rules.Variant.CaptureConstraints.*;
import static to.ax.games.checkers.rules.Variant.BoardOrientation.*;

/**
 * @author Tom Ritchford (tom@swirly.com)
 * See http://en.wikipedia.org/wiki/Draughts
 */
public enum Variant {
  BRAZILIAN(     8, 12, WHITE, true,  true, false, MAXIMIZE_PIECES_CAPTURED, WHITE_IN_TOP_LEFT),
  DERECHA(       8, 12, WHITE, true,  true, false, MAXIMIZE_PIECES_CAPTURED, BLACK_IN_TOP_LEFT),
  ENGLISH(       8, 12, BLACK, false, false, true, ALL_CAPTURES_IN_SEQUENCE, WHITE_IN_TOP_LEFT),
  INTERNATIONAL(10, 20, WHITE, true,  true, false, MAXIMIZE_PIECES_CAPTURED, WHITE_IN_TOP_LEFT),
  ;
  
  Variant(
      int boardSize, 
      int pieceCount, 
      Color firstMoveColor,
      boolean hasFlyingKings, 
      boolean canMenCaptureBackwards, 
      boolean blackPromotesWhenPassingThroughFinalRank,
      CaptureConstraints captureConstraints,
      BoardOrientation boardOrientation) {
    this.height = boardSize;
    this.width = boardSize / 2;
    this.pieceCount = pieceCount;
    this.hasFlyingKings = hasFlyingKings;
    this.canMenCaptureBackwards = canMenCaptureBackwards;
    this.firstMoveColor = firstMoveColor;
    this.blackPromotesWhenPassingThroughFinalRank = blackPromotesWhenPassingThroughFinalRank;
    this.captureConstraints = captureConstraints;
    this.boardOrientation = boardOrientation;
    this.boardSquares = boardSize * boardSize  / 2;
    this.rowsWithPieces = pieceCount / width;
    assert 0 == pieceCount % width;
  }
  
  public final int height;
  public final int width;
  public final int pieceCount;
  public final boolean hasFlyingKings;
  public final boolean canMenCaptureBackwards;
  public final Color firstMoveColor;
  public final boolean blackPromotesWhenPassingThroughFinalRank;
  public final CaptureConstraints captureConstraints;
  public final BoardOrientation boardOrientation;
  public final int boardSquares;
  public final int rowsWithPieces;
  
  public enum CaptureConstraints {
    MAXIMIZE_PIECES_CAPTURED,
    ALL_CAPTURES_IN_SEQUENCE
  };
  
  public enum BoardOrientation {
    BLACK_IN_TOP_LEFT,
    WHITE_IN_TOP_LEFT
  };
}
