package to.ax.games.chess.rules;

import static to.ax.games.chess.Piece.*;
import static to.ax.games.util.Color.*;
import static to.ax.games.chess.Piece.Type.PAWN;

import to.ax.games.chess.Board;
import to.ax.games.chess.Square;
import to.ax.games.chess.Game;
import to.ax.games.chess.Move;
import to.ax.games.chess.Piece;
import to.ax.games.util.Color;

/**
 * @author Tom Ritchford (tom@swirly.com)
 * TODO(tom): write unit tests.
 */
public class PawnRules extends PieceRules {
  public PawnRules(Color color) {
    offsets = (color == WHITE) ? WHITE_MOVES : BLACK_MOVES;
  }
  
  @Override
  public Move[] getMoves(Game game, Move move) {
    // TODO(tom): test this method.
    Square from = move.from;
    Square to = move.to;
    int rank = to.rank;
    if (rank == 0) {
      return new Move[] { 
          new Move(from, to, WHITE_KNIGHT), 
          new Move(from, to, WHITE_BISHOP), 
          new Move(from, to, WHITE_ROOK), 
          new Move(from, to, WHITE_QUEEN) 
          };
    } else if (rank == 7) {
      return new Move[] { 
          new Move(from, to, BLACK_KNIGHT), 
          new Move(from, to, BLACK_BISHOP), 
          new Move(from, to, BLACK_ROOK), 
          new Move(from, to, BLACK_QUEEN) 
          };
    } else {
      Square epSquare = game.getEpSquare();
      if (epSquare != null && from.file != to.file) {
        if (epSquare == to) {
          return new Move[] {
              new Move(from, to, null, false),
              new Move(from, to, null, true),
          };
        }
      }
    }    
    return super.getMoves(game, move);
  }
  
  protected Board adjustBoard(GameMove gameMove) {
    Board board = super.adjustBoard(gameMove);
    Move move = gameMove.move;
    if (move.enPassant) 
      board.setPiece(gameMove.game.getEpSquare(), null);
    else if (move.promotionPiece != null)
      board.setPiece(move.to, move.promotionPiece);
    return board;
  }
  
  protected Square getEnPassantSquare(GameMove gameMove) {
    // TODO(tom): test this method.
    Game game = gameMove.game;
    Move move = gameMove.move;
    
    Square from = move.from;
    Square to = move.to;
    Piece piece = game.getPiece(from);
    if (piece.type != PAWN)
      return null;
    
    int ranks = to.rank - from.rank;
    if (Math.abs(ranks) != 2) 
      return null;
    
    Piece capturingPawn = piece == BLACK_PAWN ? WHITE_PAWN : BLACK_PAWN;
    if (game.getPiece(to.add(-1, 0)) == capturingPawn ||
        game.getPiece(to.add( 1, 0)) == capturingPawn) {
      final boolean isBlack = ranks == 2;
      return to.add(0, isBlack ? -1 : 1);
    }
    return null;
  }
  
  protected int pawnOrCaptureCount(GameMove gameMove) {
    return 0;
  }
  
  @Override
  public boolean isLegalPieceMove(GameMove gameMove, boolean capture) {
    // TODO(tom): test this method.
    Square from = gameMove.getFrom();
    int[] offset = from.offset(gameMove.getTo());
    boolean sameFile = offset[0] == 0;
    if (capture == sameFile)
      return false;
    int rank = from.rank;
    boolean unmovedPawn = (rank == 1 || rank == 6);
    boolean isSingleMove = Math.abs(offset[1]) == 1;    
    return (capture || isSingleMove || unmovedPawn) && 
        super.isLegalPieceMove(gameMove, capture);
  }

  public int[][] getMoveOffsets() { 
    return offsets; 
  }
  
  private final int[][] offsets;

  public static final int[][] BLACK_MOVES = {
      {-1,  1}, {0,  1}, {1,  1},
                {0,  2},
  };
  /** An array of all the possible Pawn.White offsets as byte offsets. */
  public static final int[][] WHITE_MOVES = {  
                {0, -2},
      {-1, -1}, {0, -1}, {1, -1},
  };  
}
