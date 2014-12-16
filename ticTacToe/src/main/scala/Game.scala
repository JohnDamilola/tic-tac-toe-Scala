import exception.IllegalMoveException

import scala.io.StdIn
import scala.util.{Success, Try, Failure}

/**
 * Created by pedrorijo on 11/12/14.
 */
class Game(private[this] val startingPlayer : Int) {

  private[this] val board: Board = new Board

  def start : (Boolean, Int) = {
    println("Starting a new game")

    play(startingPlayer)
  }

  private[this] def play(player: Int): (Boolean, Int) = {

    println(board.prettyBoard)

    if (gameOver) {
      println("====================")
      println("Game ended.")

      val winner = board.hasWinner
      println(if (winner._1) "Player " + winner._2 + " won!" else "Tie!")
      println("====================\n")

      winner
    }
    else {
      println("Player " + player + " turn. Make your moves.")
      val pos = convertMove(StdIn.readLine())

      pos match {
        case Failure(e) => {
          println(e.getMessage + " Please choose a move between 'a' and 'i'")
          play(player)
        }
        case Success((line, col)) if (!allowedMove((line, col))) => {
          println("Invalid move: " + convertMove((line, col)) +
            ". Please choose an empty position.")
          play(player)
        }
        case Success((line, col)) => board.play(player, line, col); play(nextPlayer(player))
      }
    }
  }

  private[this] def gameOver: Boolean = !board.hasAvailableMoves || board.hasWinner._1

  private[this] def allowedMove(move: (Int, Int)): Boolean = board.isAvailable(move)

  private[this] def nextPlayer(player: Int) = if (player == 1) 2 else 1

  private[this] def convertMove(pos: String): Try[(Int, Int)] = {
    Try(
      pos match {
        case "a" => (0, 0)
        case "b" => (0, 1)
        case "c" => (0, 2)
        case "d" => (1, 0)
        case "e" => (1, 1)
        case "f" => (1, 2)
        case "g" => (2, 0)
        case "h" => (2, 1)
        case "i" => (2, 2)
        case _ => throw new IllegalMoveException(pos)
      })
  }

  private[this] def convertMove(pos: (Int, Int)): String = {
    pos match {
      case (0, 0) => "a"
      case (0, 1) => "b"
      case (0, 2) => "c"
      case (1, 0) => "d"
      case (1, 1) => "e"
      case (1, 2) => "f"
      case (2, 0) => "g"
      case (2, 1) => "h"
      case (2, 2) => "i"
    }
  }
}
