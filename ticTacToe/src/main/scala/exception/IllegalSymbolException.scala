package exception

/**
 * Created by pedrorijo on 13/12/14.
 */
class IllegalSymbolException(val symbol : Symbol) extends RuntimeException {

  override def getMessage = {
    "Invalid symbol: " + symbol + "."
  }
}
