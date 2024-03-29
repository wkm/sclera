package sclera.evaluator

import scala.actors.Actor._
import scala.tools.nsc.interpreter._
import java.io.{StringReader, PipedReader, PrintWriter, PipedWriter}

/**
 * sclera.evaluator.Evaluator
 * wiktor - 2011
 *
 * The evaluator is the main abstraction for processing scala code
 */
object Evaluator {

  case class Evaluate(input: String)
  case class Result(output: Any)

  val interpreter = new InterpreterWrapper()

  def errorHandler(result: String) {
    println("ERROR: "+result)
  }

  val Processor = actor {
    loop {
      react {
        case Evaluator.Evaluate(input) =>
          val result = interpreter.interpret(input)
          reply(Result(result.value))
      }
    }
  }

}