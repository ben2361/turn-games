package to.ax.games.util;

public interface Board<Piece, Square> {
  Piece getPiece(Square index);
  void setPiece(Square index, Piece piece);
}
