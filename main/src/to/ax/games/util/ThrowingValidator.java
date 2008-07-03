package to.ax.games.util;

import to.ax.games.Validator;

/**
 * @author Tom Ritchford (tom@swirly.com)
 *
 */
abstract public class ThrowingValidator<Game, Move> implements Validator<Game, Move> {
  public void checkGameMove(Game game, Move move) {
    checkGame(game);
    checkMove(move);
    throwProblem(whyIsGameMoveInvalid(game, move));
  }

  public void checkMove(Move move) {
    throwProblem(whyIsMoveInvalid(move));
  }
  
  public void checkGame(Game game) {
    throwProblem(whyIsGameInvalid(game));
  }
  
  private static void throwProblem(String problem) {
    if (problem != null)
      throw new IllegalArgumentException(problem);
  }
  
  abstract public String whyIsGameInvalid(Game game);
  abstract public String whyIsGameMoveInvalid(Game game, Move move);
  abstract public String whyIsMoveInvalid(Move move);
}
