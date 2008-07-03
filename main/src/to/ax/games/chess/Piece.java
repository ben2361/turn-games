package to.ax.games.chess;

import to.ax.games.util.Color;

public enum Piece {
  BLACK_PAWN(Color.BLACK,   Type.PAWN),
  BLACK_KNIGHT(Color.BLACK, Type.KNIGHT),
  BLACK_BISHOP(Color.BLACK, Type.BISHOP),
  BLACK_ROOK(Color.BLACK,   Type.ROOK),
  BLACK_QUEEN(Color.BLACK,  Type.QUEEN),
  BLACK_KING(Color.BLACK,   Type.KING),
  
  WHITE_PAWN(Color.WHITE,   Type.PAWN),
  WHITE_KNIGHT(Color.WHITE, Type.KNIGHT),
  WHITE_BISHOP(Color.WHITE, Type.BISHOP),
  WHITE_ROOK(Color.WHITE,   Type.ROOK),
  WHITE_QUEEN(Color.WHITE,  Type.QUEEN),
  WHITE_KING(Color.WHITE,   Type.KING);
  
  public enum Type { PAWN, KNIGHT, BISHOP, ROOK, QUEEN, KING }
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
