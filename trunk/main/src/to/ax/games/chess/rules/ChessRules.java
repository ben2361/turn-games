/**
 * 
 */
package to.ax.games.chess.rules;

import static to.ax.games.chess.Piece.*;
import static to.ax.games.util.Color.*;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Vector;

import to.ax.games.Rules;
import to.ax.games.chess.Square;
import to.ax.games.chess.Game;
import to.ax.games.chess.Move;
import to.ax.games.chess.Piece;
import to.ax.games.util.Color;
import to.ax.games.util.IsMoveValid;


/**
 * @author Tom Ritchford (tom@swirly.com)
 *
 */
public class ChessRules implements Rules<Game, Move, String> {
  public static final int MAX_MOVES_SINCE_PAWN_OR_CAPTURE = 50;
  
  private ChessRules() {}
  
  public static final ChessRules INSTANCE = new ChessRules();
  
  public Game getInitialGameState() {
    return Game.INITIAL_STATE;
  }
  
  public boolean isMoveValid(Game gameState, Move move) {
    return IsMoveValid.isMoveValid(gameState, move, this);
  }
  
  public List<Move> getMoves(Game game) {
    List<Move> moves = new Vector<Move>();
    if (game.getMovesSincePawnOrCapture() > MAX_MOVES_SINCE_PAWN_OR_CAPTURE)
      return moves;
    
    Color color = ChessRules.getColorToMove(game);
    for (Square from: PieceRules.occupiedSquareIteratable(game, color)) {
      final Piece piece = game.getPiece(from);
      PieceRules pieceRules = getPieceRules(piece);
      for (int[] offset: pieceRules.getMoveOffsets()) {
        Square to = from.add(offset);
        if (to != null) {
          Move move = new Move(from, to);
          boolean isLegalMove = pieceRules.isLegalMove(new GameMove(game, move));
          if (isLegalMove)
            moves.addAll(Arrays.asList(pieceRules.getMoves(game, move)));
        }
      }
    }
    return moves;
  }
  
  public boolean validateMove(Game gameState, Move move) {
    return getMoves(gameState).contains(move);
  }
  
  public Game applyMove(Game game, Move move) {
    return getPieceRules(game.getPiece(move.from)).applyMove(new GameMove(game, move));
  }

  static public Color getColorToMove(Game game) {
    return ((game.getMoveCount() % 2) == 0) ? WHITE : BLACK;
  }
  
  public String getResult(Game game) {
    final Color colorToMove = getColorToMove(game);
    if (KingRules.isInCheck(game, colorToMove))
      return colorToMove.not().toString().toLowerCase() + " win";
    else
      return "draw";
  }

  static PieceRules getPieceRules(Piece piece) {
    return PIECE_RULES.get(piece);
  }

  static private final EnumMap<Piece, PieceRules> PIECE_RULES 
      = new EnumMap<Piece, PieceRules>(Piece.class);

  static {
    PIECE_RULES.put(BLACK_PAWN, new PawnRules(BLACK));
    PIECE_RULES.put(BLACK_KNIGHT, new KnightRules());
    PIECE_RULES.put(BLACK_BISHOP, new BishopRules());
    PIECE_RULES.put(BLACK_ROOK, new RookRules());
    PIECE_RULES.put(BLACK_QUEEN, new QueenRules());
    PIECE_RULES.put(BLACK_KING, new KingRules());
    PIECE_RULES.put(WHITE_PAWN, new PawnRules(WHITE));
    PIECE_RULES.put(WHITE_KNIGHT, new KnightRules());
    PIECE_RULES.put(WHITE_BISHOP, new BishopRules());
    PIECE_RULES.put(WHITE_ROOK, new RookRules());
    PIECE_RULES.put(WHITE_QUEEN, new QueenRules());
    PIECE_RULES.put(WHITE_KING, new KingRules());
  }
}
