package to.ax.games.converter.chess;

import java.util.EnumSet;

import to.ax.games.chess.Square;
import to.ax.games.chess.Game;
import to.ax.games.converter.Converter;
import to.ax.games.util.Color;
import static to.ax.games.chess.Square.*;
import static to.ax.games.util.Color.*;

/**
 * @author Tom Ritchford (tom@swirly.com)
 *
 */
public class GameToString {
  public static final Converter<Game, String> WRITER = new Converter<Game, String>() {
    public String convert(Game game) {
      // TODO(tom):  write tests for this.
      StringBuffer buffer = new StringBuffer();
      final EnumSet<Square> castleSquares = game.getCastleSquaresCopy();
      if (castleSquares.contains(E1)) 
        addCastleStatusDescription(buffer, castleSquares, WHITE, A1, H1);
      
      if (castleSquares.contains(E8))
        addCastleStatusDescription(buffer, castleSquares, BLACK, A8, H8);
      
      Square epSquare = game.getEpSquare();
      if (epSquare != null) {
        if (buffer.length() > 0)
          buffer.append("\n");
        buffer.append(" en passant: ");
        buffer.append(epSquare);
      }
      return String.format("Move: %d (%d)\n\n%s\n%s", game.getMoveCount(), 
          game.getMovesSincePawnOrCapture(), game.getBoard(), buffer);
    }

    private void addCastleStatusDescription(StringBuffer buffer, EnumSet<Square> castleSquares, 
        Color color, Square queensRook, Square kingsRook) {
      boolean kingSideCastle = castleSquares.contains(kingsRook);
      boolean queenSideCastle = castleSquares.contains(queensRook);
      if (queenSideCastle || kingSideCastle) {
        buffer.append("\n" + color.toString().toLowerCase() + " can castle ");
        if (kingSideCastle) {
          buffer.append("king");
          if (queenSideCastle)
            buffer.append(" and ");
        }
        if (queenSideCastle)
          buffer.append("queen");
        buffer.append("side");
      }
    }
  };
}
