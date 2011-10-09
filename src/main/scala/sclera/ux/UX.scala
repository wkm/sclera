package sclera.ux

import actors.Actor
import actors.Actor._

/**
 * sclera.ux.UX
 * wiktor - 2011
 *
 * The core UX event processor. This is fundamentally an actor which coordinates message dispatch
 * to scratch-level actors and evaluators (generally Scala interpreters)
 */
object UX {

  case class Evaluate()

  val Processor : Actor = actor {
    loop {
      receive {
        case Evaluate() =>
          println("Evaluating")
      }
    }
  }
}