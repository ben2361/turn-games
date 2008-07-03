package to.ax.games.checkers;

import to.ax.games.util.Color;

// TODO(tom):  figure out a way squares make "Pieces" generic -- seems hard!
public enum Piece implements Comparable<Piece> {
  BLACK_PIECE(Color.BLACK, Type.PIECE),
  BLACK_KING(Color.BLACK,  Type.KING),
  WHITE_PIECE(Color.WHITE, Type.PIECE),
  WHITE_KING(Color.WHITE,  Type.KING);
  
  public enum Type { PIECE, KING }
  public static final int TYPE_COUNT = Type.values().length;
  
  Piece(Color color, Type type) {
    this.color = color;
    this.type = type;
  }

  public final Color color;
  public final Type type;
  
  public Piece not() {
    int ord = ordinal() - TYPE_COUNT;
    if (ord < 0)
      ord += 2 * TYPE_COUNT;
    return values()[ord];
  }
  
  public static Piece getPiece(Type type, Color color) {
    return values()[type.ordinal() + ((color == Color.BLACK) ? 0 : TYPE_COUNT)];
  }
  
  public static Color getColor(Piece piece) {
    return piece == null ? null : piece.color;
  }
  
  public static Type getType(Piece piece) {
    return piece == null ? null : piece.type;
  }
}
