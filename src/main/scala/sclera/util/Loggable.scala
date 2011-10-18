package sclera.util

import org.slf4j.LoggerFactory
import ch.qos.logback.classic.LoggerContext
import ch.qos.logback.classic.joran.JoranConfigurator
import ch.qos.logback.core.util.StatusPrinter

/**
 * sclera.util.Loggable
 * wiktor - 2011
 *
 * Inspired by the eponymous class in the Lift web framework but without
 * the heavy configurability.
 *
 * TODO I'm concerned by the performance hit of all these lazy values
 */
trait Loggable {
  @transient
  private lazy val loggerObject = LoggerFactory.getLogger(this.getClass)

  protected def logger = {
    Logback.hasInit
    loggerObject
  }
}

object Logback {
  lazy val hasInit = {
    val context = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]
    StatusPrinter.printInCaseOfErrorsOrWarnings(context)

    true
  }
}