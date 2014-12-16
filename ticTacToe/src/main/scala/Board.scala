import exception.IllegalSymbolException

import scala.Array._

/**
 * Created by pedrorijo on 10/12/14.
 */
class Board {

  final val DefaultSymbol: Symbol = '*
  final val Player1Symbol: Symbol = 'X
  final val Player2Symbol: Symbol = 'O
  final val LineSeparator: String = "\n- + - + -\n"
  final val ColumnSeparator: String = " | "

  private[this] val board: Array[Array[Symbol]] = Array.fill[Symbol](3, 3) {DefaultSymbol}

  def hasAvailableMoves : Boolean = board.flatten.contains(DefaultSymbol)

  def isAvailable(pos : (Int, Int)) : Boolean = board(pos._1)(pos._2) == DefaultSymbol

  def hasWinner: (Boolean, Int) = {
    val possibilities : Array[Array[Symbol]] = (board ++: board.transpose) :+ Array(board(0)(0), board(1)(1), board(2)(2)) :+ Array(board(2)(0), board(1)(1), board(0)(2))

    val winningLines : Array[Array[Symbol]] = possibilities.filter((array: Array[Symbol]) => array.forall(elem => elem != DefaultSymbol && elem == array(0)))

    if (winningLines.length > 0) {
      winningLines.head.head match {
        case Player1Symbol => (true, 1)
        case Player2Symbol => (true, 2)
        case s : Symbol => throw new IllegalSymbolException(s)
      }
    }
    else
      (false, 0)
  }

  def play(player: Int, line: Int, col: Int): Unit = {
    board(line)(col) = player match {
      case 1 => Player1Symbol
      case 2 => Player2Symbol
    }
  }

  def prettyBoard: String = {
    val translated = for{
      i <- 0 to 2
      j <- 0 to 2
    } yield Symbol(if(board(i)(j) == DefaultSymbol) (97 + (i * 3) + j).toChar.toString else board(i)(j).name)

    "\n" + translated.grouped(3).toList.map((line : IndexedSeq[Symbol]) => prettyLine(line)).mkString(LineSeparator) + "\n"
  }

  private[this] def prettyLine(line: IndexedSeq[Symbol]): String = line.map((s: Symbol) => s.name).mkString(ColumnSeparator)

}
