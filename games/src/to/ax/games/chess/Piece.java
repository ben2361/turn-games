package to.ax.games.chess;

import to.ax.games.util.Color;

public enum Piece {
  BLACK_PAWN(Color.BLACK,   Type.PAWN),
  WHITE_PAWN(Color.WHITE,   Type.PAWN),
  
  BLACK_KNIGHT(Color.BLACK, Type.KNIGHT),
  WHITE_KNIGHT(Color.WHITE, Type.KNIGHT),
  
  BLACK_BISHOP(Color.BLACK, Type.BISHOP),
  WHITE_BISHOP(Color.WHITE, Type.BISHOP),
  
  BLACK_ROOK(Color.BLACK,   Type.ROOK),
  WHITE_ROOK(Color.WHITE,   Type.ROOK),
  
  BLACK_QUEEN(Color.BLACK,  Type.QUEEN),
  WHITE_QUEEN(Color.WHITE,  Type.QUEEN),
  
  BLACK_KING(Color.BLACK,   Type.KING),  
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
    return Piece.values()[ordinal() + (color == Color.BLACK ? +1 : -1)];
  }
  
  public static Piece getPiece(Type type, Color color) {
    return Piece.values()[2 * type.ordinal() + color.ordinal()];
  }
  
  public static Color getColor(Piece piece) {
    return piece == null ? null : piece.color;
  }
  
  public static Type getType(Piece piece) {
    return piece == null ? null : piece.type;
  }
}
