package com.pt.pedrorijo91.ticTacToe

import scala.io.StdIn
import scala.util.{Success, Failure, Try}

/**
 * Created by pedrorijo on 11/12/14.
 */
object GameManager extends App{

  //val logger = LoggerFactory.getLogger(this.getClass)
  private[this] val logger = createLogger(this.getClass)

  init

  private[this] def init = {
    val rounds = askRounds

    playGame(0, 0, rounds)
  }

  private[this] def askRounds : Int = {
    println("Please enter number of rounds:")
    val rounds = Try {
      StdIn.readInt()
    }

    print("\n")

    logger.debug("Rounds ans: " + rounds)

    rounds match {
      case Failure(e) => {
        logger.debug("Error reading rounds: " + e)
        println("Please insert an integer")
        askRounds
      }
      case Success(n) if(n <= 0) => {
        logger.debug("Invalid number of rounds: " + n)
        println("Please insert a positive number")
        askRounds
      }
      case Success(n) => n
    }
  }

  private[this] def playGame(score1 : Int, score2 : Int, remainingRounds : Int): Unit = {
    val startingPlayer = askStartingPlayer

    logger.debug("Starting player: " + startingPlayer)

    val game = new Game(startingPlayer)

    val winner = game.start

    logger.debug("Game winner:" +  winner)

    val p1Score = score1 + (if(winner._1 && winner._2 == 1) 1 else 0)
    val p2Score = score2 + (if(winner._1 && winner._2 == 2) 1 else 0)

    logger.debug("Score - P1: " + p1Score + ", P2: " + p2Score)

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

  private[this] def askRematch : Unit = {
    println("Rematch ? (y/n)")

    val ans = StdIn.readChar().toLower

    logger.debug("rematch ans: " + ans)

    ans match {
      case 'y' => print("\n"); init
      case 'n' => Unit
      case _ => {
        logger.debug("Invalid ans: " + ans)

        println("Please insert 'y' or 'n'")
        askRematch
      }
    }
  }

  private[this] def askStartingPlayer : Int = {
    println("Which Player should start ? (1/2)")

    val ans = StdIn.readInt()

    logger.debug("starting player ans: " + ans)

    ans match {
      case 1 | 2 => ans
      case _ => {
        logger.debug("Invalid ans: " + ans)

        println("Please insert '1' or '2'")
        askStartingPlayer
      }
    }
  }

}


