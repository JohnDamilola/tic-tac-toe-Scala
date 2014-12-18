package com.pt.pedrorijo91.ticTacToe

import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.classic.{Logger, LoggerContext}
import ch.qos.logback.core.FileAppender
import org.slf4j.LoggerFactory

import scala.io.StdIn

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
    val rounds = StdIn.readInt()
    print("\n")

    logger.debug("Rounds: " + rounds)

    rounds
  }

  private[this] def playGame(score1 : Int, score2 : Int, remainingRounds : Int): Unit = {
    val startingPlayer = askStartingPlayer

    logger.debug("Starting player: " + startingPlayer)

    val game = new Game(startingPlayer)

    val winner = game.start

    logger.debug("com.pt.pedrorijo91.ticTacToe.Game winner:" +  winner)

    val p1Score = score1 + (if(winner._1 && winner._2 == 1) 1 else 0)
    val p2Score = score2 + (if(winner._1 && winner._2 == 2) 1 else 0)

    logger.debug("Score - P1: " + p1Score + ", P2: " + p2Score)

    if(remainingRounds - 1 > 0) {
      playGame(p1Score, p2Score, remainingRounds - 1)
    }
    else {
      println("com.pt.pedrorijo91.ticTacToe.Game Over. Player 1 : " + p1Score + " vs Player 2 : " + p2Score + ".")

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


