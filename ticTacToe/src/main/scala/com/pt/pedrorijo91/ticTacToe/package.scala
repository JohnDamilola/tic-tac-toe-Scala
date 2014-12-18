package com.pt.pedrorijo91

import ch.qos.logback.classic.Logger
import org.slf4j.LoggerFactory

/**
 * Created by pedrorijo on 18/12/14.
 */
package object ticTacToe {

  def createLogger(clazz : Class[_]) : Logger = LoggerFactory.getLogger(clazz).asInstanceOf[Logger]


}
