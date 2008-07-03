package to.ax.games.converter.chess;

import to.ax.games.chess.Board;
import to.ax.games.chess.Square;
import to.ax.games.chess.Piece;
import to.ax.games.converter.Converter;

public class BoardToDisplay implements Converter.TwoWay<Board, String> {
  private BoardToDisplay() {}
  public static final BoardToDisplay INSTANCE = new BoardToDisplay();
  
  public Converter<String, Board> getConvertToFrom() { return READER; }  
  public Converter<Board, String> getConvertFromTo() { return WRITER; }
  
  private static final Converter<String, Board> READER = new Converter<String, Board>() {
    public Board convert(String str) {
      Board board = new Board();
      int charPos = 0;
      for (Square square: Square.values()) {
        char symbol = str.charAt(charPos);
        Piece piece = PieceToCharacter.READER.convert(symbol);
        board.setPiece(square, piece);
        charPos += 2;
        if (square.file == 7) {
          assert symbol == '\n';
          ++charPos;
        }
      }
      return board;
    }
  };
  
  public static final Converter<Board, String> WRITER = new Converter<Board, String>() {
    public String convert(Board board) {
      StringBuffer buffer = new StringBuffer();
      for (Square square: Square.values()) {
        Piece piece = board.getPiece(square);
        buffer.append(PieceToCharacter.WRITER.convert(piece));
        buffer.append(' ');
        if (square.file == 7)
          buffer.append('\n');
      }
      return buffer.toString();
    }
  };
}