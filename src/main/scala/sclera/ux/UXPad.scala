package sclera.ux

import swing._
import event.{WindowDeactivated, WindowActivated, FocusGained}
import javax.swing.border.EtchedBorder
import wrappers.{JNestedTextComponent, NestedTextComponent}
import javax.swing.{JLabel, JTextPane, JPanel, BorderFactory}
import java.awt.{Dimension, BorderLayout, Color}
import actors.Actor
import actors.Actor._
import sclera.util.Loggable
import java.io.{StringWriter, StringReader}
import sclera.evaluator.Evaluator

/**
 * Represents a single Sclera pad/file
 */
class UXPad extends MainFrame with Loggable {
  var boxPanel = new NestedTextComponent {
    contents += new UXPadEntry(UXPad.this, "12 + 13", isInput = true)
    contents += new UXPadEntry(UXPad.this, "15")
  }

  menuBar = new MenuBar() {
    contents += new Menu("File")
    contents += new Menu("Edit")
    contents += new Menu("Help") {
      contents += new MenuItem("item 1")
      contents += new MenuItem("item 2")
      contents += new RadioMenuItem("radio item")
      contents += new CheckMenuItem("check item ")
    }
  }

  
  title = "Sclera"
  contents = new ScrollPane() {
    contents = boxPanel
  }
  size = new Dimension(400, 500)

  listenTo(this)
  reactions += {
    case WindowActivated(source) =>
      logger.trace("WindowActivated")
      if(source == peer)
        UX.Processor !? UX.Focus(this)

    case WindowDeactivated(source) =>
      logger.trace("WindowDeactivated")
      if(source == peer)
        UX.Processor !? UX.LostFocus(this)
  } 

  var focusedEntry: Option[UXPadEntry] = None

  val processor: Actor = actor {
    loop {
      receive {
        case msg@ UX.ChangeEntryFocus(entry) =>
          logger.trace("{}", entry)
          focusedEntry = Option(entry)
          reply(UX.Handled(msg))

        case msg@ UX.LoseEntryFocus(entry) =>
          logger.trace("{}", entry)
          if(focusedEntry == entry)
            focusedEntry = None
          reply(UX.Handled(msg))

        case UX.Evaluate() =>
          logger.trace("UX.Evaluate")
          if(focusedEntry.isDefined) {
            val entry = focusedEntry.get
            Evaluator.Processor ! Evaluator.Evaluate(entry.entryContents.get)
          } else
            logger.trace("\tno entry in focus")
      }
    }
  }
}