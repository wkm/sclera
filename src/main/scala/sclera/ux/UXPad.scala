package sclera.ux

import swing._
import javax.swing.border.EtchedBorder
import java.awt.{BorderLayout, Color, Dimension}
import javax.swing.{JTextPane, JPanel, BorderFactory}
import java.io.StringReader

/**
 * Represents a single Sclera pad/file
 */
class UXPad extends MainFrame {
  var boxPanel = new Component {
    override lazy val peer = new JTextPane()

    peer.insertComponent(createPane())
    peer.insertComponent(createPane())
    peer.setEditable(false)

    def createPane() = {
      val panel = new JPanel()
      panel.setLayout(new BorderLayout)

      val textpane = new JTextPane()
      textpane.setBorder(BorderFactory.createMatteBorder(0,0,1,2,Color.BLUE))

      panel.add(textpane)

      peer.getEditorKit.read(new StringReader("\n"), peer.getDocument, peer.getDocument.getLength)

      panel
    }
  } 
  
//  boxPanel.contents += new UXPadEntry("a")
//  boxPanel.contents += new UXPadEntry("b")

//  boxPanel.contents += new UXPadEntry("a")
//  boxPanel.contents += new UXPadEntry("b")
//  boxPanel.contents += new UXPadEntry("c")

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
  contents = boxPanel
  size = new Dimension(600, 800)
}