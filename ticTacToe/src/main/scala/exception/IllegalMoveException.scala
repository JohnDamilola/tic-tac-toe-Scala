package exception

/**
 * Created by pedrorijo on 14/12/14.
 */
class IllegalMoveException(val pos : String) extends RuntimeException {

  override def getMessage : String = {
    "Invalid move: " + pos + "."
  }
}