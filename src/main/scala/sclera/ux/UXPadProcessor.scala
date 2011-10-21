package sclera.ux

import actors.Actor._
import sclera.evaluator.Evaluator
import sclera.util.Loggable

/**
 * sclera.ux.UXPadProcessor
 * wiktor - 2011
 */

class UXPadProcessor (
    val pad: UXPad
)
  extends Loggable
{
  var focusedEntry: Option[UXPadEntry] = None
  
  val processor = actor {
    loop {
      react {
        case msg@ UX.ChangeEntryFocus(entry) =>
          logger.trace("{}", entry)
          focusedEntry = Option(entry)
          reply(UX.Handled(msg))

        case msg@ UX.LoseEntryFocus(entry) if(focusedEntry == entry) =>
          focusedEntry = None
          reply(UX.Handled(msg))

        case msg@ UX.LoseEntryFocus(_) =>
          reply(UX.Handled(msg))

        case UX.Evaluate() if focusedEntry.isDefined =>
          logger.trace("UX.Evaluate")
          focusedEntry.get match {
            case input: UXPadInputEntry =>
              Evaluator.Processor ! Evaluator.Evaluate(input.entryContents.get)

            case _ =>
              logger.error("Cannot execute non-input entry")
          }

        case UX.Evaluate() =>
          logger.warn("\tno entry in focus")

        case Evaluator.Result(value) =>
          logger.trace("Evaluator.Result")
          logger.trace("\t=> {}", value)

          pad.insertEntry(new UXPadOutputEntry(value))
        }
      }
    }
}