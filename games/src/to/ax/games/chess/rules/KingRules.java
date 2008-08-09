package to.ax.games.chess.rules;

import static to.ax.games.chess.Square.*;
import static to.ax.games.chess.Piece.BLACK_KING;
import static to.ax.games.chess.Piece.WHITE_KING;
import static to.ax.games.util.Color.*;

import java.util.EnumSet;

import to.ax.games.chess.Board;
import to.ax.games.chess.Square;
import to.ax.games.chess.Game;
import to.ax.games.chess.Move;
import to.ax.games.chess.Piece;
import to.ax.games.util.Color;

/**
 * @author Tom Ritchford (tom@swirly.com)
 */
public class KingRules extends PieceRules {
  @Override
  public int[][] getMoveOffsets() { return MOVES; }  
  
  @Override
  protected Board adjustBoard(GameMove gameMove) {
    Board board = super.adjustBoard(gameMove);
    if (isCandidateCastleMove(gameMove)) {
      Move move = getCastleRookMove(gameMove);
      if (move != null)
        board = applyMoveToBoard(move, board);
    }
    return board;
  }
  
  private static Square getKingHomeSquare(Game game) {
    final Color color = ChessRules.getColorToMove(game);
    return (color == WHITE) ? E1 : E8;
  }

  @Override
  public boolean isLegalPieceMove(GameMove gameMove, boolean capture) {
    if (isCandidateCastleMove(gameMove))
      return !capture && getCastleRookMove(gameMove) != null;
    else
      return super.isLegalPieceMove(gameMove, capture);
  }

  private static boolean isCandidateCastleMove(GameMove gameMove) {
    int files = gameMove.getFrom().file - gameMove.getTo().file;
    boolean isCandidateCastleMove = (files == 2 || files == -2);
    return isCandidateCastleMove;
  }
  
  public Move getCastleRookMove(GameMove gameMove) {
    // TODO(tom): test this method.
    Game game = gameMove.game;
    Square kingSquare = gameMove.getFrom();
    if (kingSquare != getKingHomeSquare(game))
      return null;
    
    Square toSquare = gameMove.getTo();
    boolean isKingSideCastle = toSquare.file > kingSquare.file;
    
    Square rookSquare = kingSquare.next(isKingSideCastle ? 3 : -4);
    assert rookSquare != null;
    if (rookSquare == null)  // TODO(tom): why doesn't assert work?!
      throw new RuntimeException();
    
    // Make sure the king and room have never moved.
    if (!(game.getCastleSquaresCopy().containsAll(EnumSet.of(kingSquare, rookSquare))))
      return null;
    
    Square rookTarget = kingSquare.next(isKingSideCastle ? 1 : -1);
    Square squareBesideRook = rookSquare.next(isKingSideCastle ? -1 : 1);
    
    Color opponentColor = ChessRules.getColorToMove(game).not();
    for (Square square: EnumSet.of(kingSquare, rookTarget, toSquare)) {
      if (isAttacked(game, square, opponentColor))
        return null;
    }
    
    for (Square square: EnumSet.of(rookTarget, toSquare, squareBesideRook)) {
      if (isAttacked(game, square, opponentColor))
        return null;
    }
    
    return new Move(rookSquare, rookTarget);
  }
  
  public static boolean isInCheck(Game game, Color checkedColor) {
    Piece checkedKing = (checkedColor == WHITE) ? WHITE_KING : BLACK_KING;
    Square kingSquare = game.getSquareForPiece(checkedKing);    
    return PieceRules.isAttacked(game, kingSquare, checkedColor.not());
  }
  public static final EnumSet<Square> CASTLE_MOVE_SQUARES = EnumSet.of(A8, E8, H8, A1, E1, H1);
  
  public static final int[][] MOVES = {
                  {-1, -1 }, { 0, -1 }, { 1, -1 }, 
      {-2,  0 },  {-1,  0 },            { 1,  0 }, { 2,  0 },
                  {-1,  1 }, { 0,  1 }, { 1,  1 }
  };
}
