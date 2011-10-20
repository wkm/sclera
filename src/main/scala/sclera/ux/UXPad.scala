package sclera.ux

import swing._
import event.{WindowDeactivated, WindowActivated}
import wrappers.NestedTextComponent
import java.awt.Dimension
import actors.Actor
import sclera.util.Loggable
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

  val processor: Actor = new UXPadProcessor(this).processor
}