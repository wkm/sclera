package sclera.ux

import swing._
import javax.swing.border.EtchedBorder
import java.io.StringReader
import wrappers.{JNestedTextComponent, NestedTextComponent}
import javax.swing.{JLabel, JTextPane, JPanel, BorderFactory}
import java.awt.{Dimension, BorderLayout, Color}

/**
 * Represents a single Sclera pad/file
 */
class UXPad extends MainFrame {
  var boxPanel = new NestedTextComponent {
    contents += new UXPadEntry("12 + 13", isInput = true)
    contents += new UXPadEntry("15")
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
}