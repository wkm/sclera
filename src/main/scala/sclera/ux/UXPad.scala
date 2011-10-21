package sclera.ux

import formatting.OutputFormatting
import wrappers.NestedTextComponent
import java.awt.Dimension
import actors.Actor
import sclera.util.Loggable
import sclera.format.color.SolarizedColorPalette
import swing._
import event.{WindowDeactivated, WindowActivated}
import sclerakit.ux.{Table, RenderDispatch}

/**
 * Represents a single Sclera pad/file
 */
class UXPad extends MainFrame with Loggable {
  var boxPanel = new NestedTextComponent {
    add(new UXPadInputEntry(UXPad.this))
    add(UXPadEntry(
      Table(
        List(
          List(new java.util.Date, 2, 3),
          List("a", "b", 3123)
        )
      )
    ))
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
  background = SolarizedColorPalette("white")

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

  def insertEntry(entry: UXPadEntry) {
    boxPanel.add(entry)
    boxPanel.revalidate()
  }
}