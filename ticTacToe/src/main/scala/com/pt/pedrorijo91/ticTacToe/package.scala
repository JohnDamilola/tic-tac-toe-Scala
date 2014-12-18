package com.pt.pedrorijo91.ticTacToe

import ch.qos.logback.classic.{Logger, LoggerContext}
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.FileAppender
import org.slf4j.LoggerFactory

/**
 * Created by pedrorijo on 18/12/14.
 */
package object ticTacToe {

  private[this] final val logFilename = "log.log"

  def createLogger(clazz : Class[_]) = {
    val lc : LoggerContext = (LoggerFactory.getILoggerFactory).asInstanceOf[LoggerContext]

    val ple = new PatternLayoutEncoder

    ple.setPattern("%date %level [%thread] %logger{10} [%file:%line] %msg%n")
    ple.setContext(lc)
    ple.start()

    val fileAppender : FileAppender[ILoggingEvent] = new FileAppender[ILoggingEvent]

    fileAppender.setFile(logFilename)
    fileAppender.setContext(lc)
    fileAppender.setEncoder(ple)
    fileAppender.start()
    fileAppender.setAppend(false)
    fileAppender.setPrudent(false)

    val logger : Logger = LoggerFactory.getLogger(clazz).asInstanceOf[Logger]
    logger.addAppender(fileAppender)
    logger.setAdditive(false)

    logger
  }

}
