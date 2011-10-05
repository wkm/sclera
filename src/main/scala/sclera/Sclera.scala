
package sclera

import swing._
import swing.Color
import event.EditDone
import java.awt.{Dimension, Color}
import javax.swing.BorderFactory
import ux.UXPadEntry

object Sclera extends SimpleSwingApplication {
  var boxPanel = new BoxPanel(Orientation.Vertical)
  boxPanel.contents += new UXPadEntry("a")
  boxPanel.contents += new UXPadEntry("b")
  boxPanel.contents += new UXPadEntry("c")

  // fully left align
  boxPanel.xLayoutAlignment = 0
  boxPanel.background = Color.WHITE
  
  def top = new MainFrame {
    title = "Sclera"
    contents = new ScrollPane() {contents = boxPanel}
    size = new Dimension(600, 800)
  }
}