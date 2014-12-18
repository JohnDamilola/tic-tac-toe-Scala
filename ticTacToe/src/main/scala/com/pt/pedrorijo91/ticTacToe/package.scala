package com.pt.pedrorijo91

import ch.qos.logback.classic.{Logger, LoggerContext}
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.FileAppender
import org.slf4j.LoggerFactory

/**
 * Created by pedrorijo on 18/12/14.
 */
package object ticTacToe {

  def createLogger(clazz : Class[_]) = {

     LoggerFactory.getLogger(clazz)

  }

}
