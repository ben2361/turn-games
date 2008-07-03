package to.ax.games.converter;

/**
 * @author Tom Ritchford (tom@swirly.com)
 *
 */
public interface Converter<From, To> {
  To convert(From from);
  
  interface TwoWay<From, To> {
    Converter<From, To> getConvertFromTo();
    Converter<To, From> getConvertToFrom();
  }
}
