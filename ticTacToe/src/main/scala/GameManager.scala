import scala.io.StdIn

/**
 * Created by pedrorijo on 11/12/14.
 */
object GameManager extends App{

   println("Please enter number of rounds")
   val rounds = StdIn.readInt()

  playGame(new Game, 0, 0, rounds)

  def playGame(game : Game, score1 : Int, score2 : Int, remainingRounds : Int): Unit = {
    val winner = game.start

    val p1Score = score1 + (if(winner._1 && winner._2 == 1) 1 else 0)
    val p2Score = score2 + (if(winner._1 && winner._2 == 2) 1 else 0)

    if(remainingRounds - 1 > 0) {
      playGame(new Game, p1Score, p2Score, remainingRounds - 1)
    }
    else {
      println("Game Over. Player 1 : " + p1Score + " - Player 2 : " + p2Score + ".")
      println(if(p1Score > p2Score) "Player 1 wins!" else if(p2Score > p1Score) "Player 2 wins!" else "You've tied ! Go for a rematch!")
    }
  }

}


