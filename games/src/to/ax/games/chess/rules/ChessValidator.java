package to.ax.games.chess.rules;

import static to.ax.games.chess.Piece.Type.*;
import static to.ax.games.util.Color.*;
import to.ax.games.chess.Game;
import to.ax.games.chess.Move;
import to.ax.games.chess.Piece.Type;
import to.ax.games.util.ThrowingValidator;

/**
 * @author Tom Ritchford (tom@swirly.com)
 *
 */
public class ChessValidator extends ThrowingValidator<Game, Move> {
  private ChessValidator() {}
  
  public static final ChessValidator INSTANCE = new ChessValidator();
  
  public String whyIsGameInvalid(Game game) {
    // TODO(tom)
    return null;
  }
  
  public String whyIsGameMoveInvalid(Game game, Move move) {
    // TODO(tom)
    return null;
  }  
  
  /** Belt-n-suspenders check that a Move is OK after all other calculations are done. */
  public String whyIsMoveInvalid(Move move) {
    if (move.from == null)
      return "No from square.";
    
    if (move.to == null)
      return "No squares square.";
    
    if (move.promotionPiece != null) {
      if (move.enPassant)
        return "Can't both be a promotion and e.p.";
  
      final Type type = move.promotionPiece.type;
      if (type == PAWN || type == KING)
        return "Can't promote squares " + type;
      
      if (move.promotionPiece.color == WHITE) {
        if (move.from.rank != 1 && move.to.rank != 0)
          return "White promotion at wrong rank. " + move;
      } else {
        if (move.from.rank != 6 && move.to.rank != 7)
          return "Black promotion at wrong rank. " + move;
      }
      if (Math.abs(move.from.file - move.to.file) > 1)
        return "Pawns can move at most one file. " + move;
    }
    
    if (move.enPassant) {
      if (move.from.file == move.to.file)
        return "e.p. must move forward. " + move;
      int ranks = move.to.rank - move.from.rank;
      if (ranks == 1) {
        // Black is capturing a white pawn, e.p.
        if (move.to.rank != 5)
          return "Black e.p. must be on the third rank. " + move;
      } else if (ranks == -1) {
        if (move.to.rank != 2)
          return "Black e.p. must be on the sixth rank. " + move;
      } else {
        return "Black e.p. must only move one square. " + move;
      }
    }
    
    return null;
  }
}