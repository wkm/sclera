package sclera.ux

import java.awt.{Color, Dimension}
import swing._

/**
 * Represents a single Sclera pad/file
 */
class UXPad extends MainFrame {
  var boxPanel = new UXPadEntryGroup()
  boxPanel.contents += new UXPadEntry("a")
  boxPanel.contents += new UXPadEntry("b")
  boxPanel.contents += new UXPadEntry("c")

  menuBar = new MenuBar() {
    contents += new Menu("Item A")
    contents += new Menu("Item B")
    contents += new Menu("Item C")
    contents += new Menu("Item D") {
      contents += new MenuItem("A")
      contents += new MenuItem("B")
      contents += new RadioMenuItem("radio")
      contents += new CheckMenuItem("check")
    }
  }

  
  title = "Sclera"
  contents = new ScrollPane() {
    contents = boxPanel
  }
  size = new Dimension(600, 800)
}