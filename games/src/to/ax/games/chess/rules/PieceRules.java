package to.ax.games.chess.rules;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Iterator;

import to.ax.games.chess.Board;
import to.ax.games.chess.Square;
import to.ax.games.chess.Game;
import to.ax.games.chess.Move;
import to.ax.games.chess.Piece;
import to.ax.games.util.Color;
import to.ax.games.util.Filter;
import to.ax.games.util.FilteredIterator;


/**
 * @author Tom Ritchford (tom@swirly.com)
 *
 */
abstract public class PieceRules {
  abstract public int[][] getMoveOffsets();
  public Move[] getMoves(Game game, Move move) {
    return new Move[] { move };
  }
  
  public boolean isLegalPieceMove(GameMove gameMove, boolean capture) { 
    return true; 
  }
  
  public final boolean isLegalMove(GameMove gameMove) {
    // TODO(tom): test this method.
    Piece toPiece = gameMove.getToPiece();    
    boolean isCapture = (toPiece != null);
    return !isSelfCapture(gameMove, toPiece, isCapture) && 
        canLegallyMovePieceBetween(gameMove, isCapture) && 
         !resultsInCheck(gameMove);
  }
  private boolean isSelfCapture(GameMove gameMove, Piece toPiece,
      boolean isCapture) {
    Piece fromPiece = gameMove.getFromPiece();    
    return isCapture && (toPiece.color == fromPiece.color);
  }
  
  private boolean canLegallyMovePieceBetween(GameMove gameMove, boolean isCapture) {
    return !isPieceBetween(gameMove) && 
        isLegalPieceMove(gameMove, isCapture);
  }
  
  private static boolean resultsInCheck(GameMove gameMove) {
    Game game = gameMove.game;
    Game gameAfterMove = ChessRules.INSTANCE.applyMove(game, gameMove.move);
    Color checkedColor = ChessRules.getColorToMove(game);
    return KingRules.isInCheck(gameAfterMove, checkedColor);
  }
  
  protected boolean isPieceBetween(GameMove gameMove) {
    return gameMove.isPieceBetween();
  }
  
  public final Game applyMove(GameMove gameMove) {    
    return new Game(
        adjustBoard(gameMove), 
        gameMove.game.getMoveCount() + 1, 
        pawnOrCaptureCount(gameMove), 
        applyMoveToCastleSquares(gameMove),
        getEnPassantSquare(gameMove));
  }
  
  static Board applyMoveToBoard(Move move, Board board) {
    Square from = move.from;
    board.setPiece(move.to, board.getPiece(from));
    board.setPiece(from, null);
    return board;
  }

  protected Square getEnPassantSquare(GameMove gameMove) { 
    return null; 
  }
  
  protected int pawnOrCaptureCount(GameMove gameMove) { 
    return (gameMove.game.getPiece(gameMove.move.to) == null) ? (gameMove.game.getMovesSincePawnOrCapture() + 1) : 0;
  }
  
  protected Board adjustBoard(GameMove gameMove) {
    return applyMoveToBoard(gameMove.move, gameMove.game.getBoardCopy());
  }
  
  static protected boolean isAttacked(Game game, Square target, Color attackingColor) {
    for (Square square: occupiedSquareIteratable(game, attackingColor)) {
      if (square == target)
        continue;
      Piece piece = game.getPiece(square);
      PieceRules pieceRules = ChessRules.getPieceRules(piece);
      int[] diff = target.offset(square);
      for (int[] offset: pieceRules.getMoveOffsets()) {
        if (Arrays.equals(diff, offset)) {
          Move move = new Move(square, target);
          if (pieceRules.canLegallyMovePieceBetween(new GameMove(game, move), true))
            return true;
        }
      }
    }
    
    return false;
  }

  private EnumSet<Square> applyMoveToCastleSquares(GameMove gameMove) {
    EnumSet<Square> castleSquares = gameMove.game.getCastleSquaresCopy();
    castleSquares.removeAll(EnumSet.of(gameMove.getFrom(), gameMove.getTo()));
    return castleSquares;
  }
  
  static Iterable<Square> occupiedSquareIteratable(final Game game, final Color color) {
    return new Iterable<Square>() {
      public Iterator<Square> iterator() {
        Filter<Square> filter = new Filter<Square>() {
          public boolean accepts(Square item) {
            Piece piece = game.getPiece(item);
            return piece != null && piece.color == color;
          }
        };
        return new FilteredIterator<Square>(filter, Arrays.asList(Square.values()).iterator());
      }
    };    
  }
}