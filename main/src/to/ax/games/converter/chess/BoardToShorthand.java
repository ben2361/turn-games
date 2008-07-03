package to.ax.games.converter.chess;

import to.ax.games.chess.Board;
import to.ax.games.chess.Square;
import to.ax.games.chess.Piece;
import to.ax.games.converter.Converter;

public class BoardToShorthand implements Converter.TwoWay<Board, String> {
  private BoardToShorthand() {}
  public static final BoardToShorthand INSTANCE = new BoardToShorthand();
  
  public Converter<Board, String> getConvertFromTo() { return WRITER; }
  public Converter<String, Board> getConvertToFrom() { return READER; }
  
  public static final Converter<String, Board> READER = new Converter<String, Board>() {
    public Board convert(String from) {
      Board board = new Board();
      for (Square square: Square.values())
        board.setPiece(square, null);
      
      Square square = Square.values()[0];
      for (int i = 0; i < from.length(); ++i) {
        char ch = from.charAt(i);
        if (Character.isWhitespace(ch))
          continue;
        else if (Character.isDigit(ch))
          square = square.next(ch - '0');
        else if (ch == '/')
           square = square.add(-square.file, 1);
        else {
          Piece piece = PieceToCharacter.READER.convert(ch);
          assert piece != null;
          if (square == null) {
            System.out.println(piece);
            assert square != null;
          }
          board.setPiece(square, piece);
          if (square.file != 7)
            square = square.next();
        }
      }
      return board;
    }    
  };
  
  public static final Converter<Board, String> WRITER = new Converter<Board, String>() {
    public String convert(Board board) {
      StringBuffer result = new StringBuffer();
      int spaces = 0;
      for (Square square: Square.values()) {
        if (square.file == 0 && square.rank != 0) {
          spaces = 0;
          result.append('/');
        }
        Piece piece = board.getPiece(square);
        if (piece == null) {
          ++spaces;
        } else { 
          if (spaces != 0) {
            result.append("" + spaces);
            spaces = 0;
          }
          result.append(PieceToCharacter.WRITER.convert(piece));
        }
      }
        
      return result.toString();
    }    
  };
}