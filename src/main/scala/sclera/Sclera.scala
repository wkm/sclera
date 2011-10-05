
package sclera

import swing._
import swing.Color
import event.EditDone
import java.awt.{Dimension, Color}

object Sclera extends SimpleSwingApplication {
  var boxPanel = new BoxPanel(Orientation.Vertical)
  boxPanel.contents += new Label("a")
  boxPanel.contents += new Label("b")
  boxPanel.contents += new Label("c")

  // fully left align
  boxPanel.xLayoutAlignment = 0
  boxPanel.background = Color.WHITE



  def top = new MainFrame {
    title = "Sclera"
    contents = new ScrollPane() {contents = boxPanel}
    size = new Dimension(300, 500)
  }
}