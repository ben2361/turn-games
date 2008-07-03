package to.ax.games;

public interface Validator<Game, Move> {
  String whyIsGameInvalid(Game game);
  String whyIsMoveInvalid(Move move);
  String whyIsGameMoveInvalid(Game game, Move move);
}