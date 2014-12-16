import scala.io.StdIn

/**
 * Created by pedrorijo on 11/12/14.
 */
object GameManager extends App{

  init

  private def init = {
    val rounds = askRounds

    playGame(0, 0, rounds)
  }

  private def askRounds : Int = {
    println("Please enter number of rounds:")
    val rounds = StdIn.readInt()
    print("\n")
    rounds
  }

  private def playGame(score1 : Int, score2 : Int, remainingRounds : Int): Unit = {
    val startingPlayer = askStartingPlayer

    val game = new Game(startingPlayer)

    val winner = game.start

    val p1Score = score1 + (if(winner._1 && winner._2 == 1) 1 else 0)
    val p2Score = score2 + (if(winner._1 && winner._2 == 2) 1 else 0)

    if(remainingRounds - 1 > 0) {
      playGame(p1Score, p2Score, remainingRounds - 1)
    }
    else {
      println("Game Over. Player 1 : " + p1Score + " vs Player 2 : " + p2Score + ".")

      println((p1Score, p2Score) match {
        case (s1,s2) if(s1 > s2) => "Player 1 wins!\n"
        case (s1, s2) if(s2 > s2) => "Player 2 wins!\n"
        case _ => "You've tied ! Go for a rematch!\n"
      })

      askRematch
    }
  }

  private def askRematch : Unit = {
    println("Rematch ? (y/n)")

    val ans = StdIn.readChar().toLower
    ans match {
      case 'y' => print("\n"); init
      case 'n' => Unit
      case c => println("Please insert 'y' or 'n'"); askRematch
    }
  }

  private def askStartingPlayer : Int = {
    println("Which Player should start ? (1/2)")

    val ans = StdIn.readInt()
    ans match {
      case 1 | 2 => ans
      case _ => println("Please insert '1' or '2'"); askStartingPlayer
    }
  }

}


