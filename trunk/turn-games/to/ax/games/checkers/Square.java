/**
 * 
 */
package to.ax.games.checkers;

/**
 * @author Tom Ritchford (tom@swirly.com)
 *
 */
public enum Square {
  S01, S02, SO3, SO4, SO5, SO6, SO7, SO8, SO9, S10,
  S11, S12, S13, S14, S15, S16, S17, S18, S19, S20,
  S21, S22, S23, S24, S25, S26, S27, S28, S29, S30,
  S31, S32, S33, S34, S35, S36, S37, S38, S39, S40,
  S41, S42, S43, S44, S45, S46, S47, S48, S49, S50,
  S51, S52, S53, S54, S55, S56, S57, S58, S59, S60,
  S61, S62, S63, S64, S65, S66, S67, S68, S69, S70,
  S71, S72, S73, S74, S75, S76, S77, S78, S79, S80,
  S81, S82, S83, S84, S85, S86, S87, S88, S89, S90,
  S91, S92, S93, S94, S95, S96, S97, S98, S99, S100,
  // This is more than we need -- just add more if we need them!
  ;

  public int offset(Square square) {
    return ordinal() - square.ordinal();
  }
  
  public Square next(int difference, int boardSquares) {
    int i = ordinal() + difference;
    return (i >= 0 && i < boardSquares) ? Square.values()[i] : null;
  }
  
  
  public Square next(int boardSquares) { return next(1); }    
}
