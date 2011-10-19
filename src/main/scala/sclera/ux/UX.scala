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

  sealed abstract class UXEvent
  case class Focus(pad: UXPad) extends UXEvent
  case class LostFocus(pad: UXPad) extends UXEvent
  case class Handled(msg: Any) extends UXEvent
  case class Evaluate() extends UXEvent

  case class ChangeEntryFocus(entry: UXPadEntry) extends UXEvent
  case class LoseEntryFocus(entry: UXPadEntry) extends UXEvent



  var focusedPad: Option[UXPad] = None

  val Processor : Actor = actor {
    loop {
      react {
        case Focus(pad) =>
          println("focused")
          focusedPad = Some(pad)
          reply(Handled(Focus(pad)))

        case LostFocus(pad) =>
          println("Lost focus")
          if(focusedPad.isDefined && focusedPad.get == pad)
            focusedPad = None
          
          // even if somehow this is isn't the focused pad, reply
          // otherwise we will freeze
          reply(Handled(LostFocus(pad)))

        case Evaluate() =>
          if(focusedPad.isEmpty)
            println("no pad in focus")
          else
            focusedPad.get.processor ! Evaluate()
      }
    }
  }
}