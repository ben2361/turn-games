package to.ax.games.chess.rules;

import to.ax.games.chess.Square;
import to.ax.games.chess.Game;
import to.ax.games.chess.Move;
import to.ax.games.chess.Piece;

/**
 * @author Tom Ritchford (tom@swirly.com)
 *
 */
class GameMove {  
  final Game game;
  final Move move;
  
  GameMove(Game game, Move move) {
    this.game = game;
    this.move = move;
  }

  Square getFrom() { return move.from; }
  Square getTo() { return move.to; }
  Piece getFromPiece() { return game.getPiece(getFrom()); }
  Piece getToPiece() { return game.getPiece(getTo()); }

  boolean isPieceBetween() {
    Square from = getFrom();
    Square to = getTo();
    if (from == to)
      return false;
    int[] diff = to.offset(from);
    int distance = Math.max(Math.abs(diff[0]), Math.abs(diff[1]));
    int delta = Square.measure(diff[0] / distance, diff[1] / distance);
    for (int i = 1; i < distance; ++i) {
      Square next = from.next(delta * i);
      if (game.getPiece(next) != null) 
        return true;
    }
    return false;
  }
}
