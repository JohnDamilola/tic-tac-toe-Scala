package exception

/**
 * Created by pedrorijo on 14/12/14.
 */
class IllegalMoveException(val pos : String) extends RuntimeException {
  println("created IllegalMoveException with " + pos)

  override def getMessage = {
    "Invalid move: " + pos + "."
  }
}