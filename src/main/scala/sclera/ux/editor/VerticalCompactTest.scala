package sclera.ux.editor

import javax.swing.JTextPane
import swing._
import java.io.StringReader
import sclera.util.SwingKit
import javax.swing.text.{StyledEditorKit, DefaultEditorKit}

/**
 * sclera.ux.editor.VerticalCompactTest
 * wiktor - 2011
 */

object VerticalCompactTest extends SimpleSwingApplication {
  def top = new MainFrame {
    contents = new BoxPanel(Orientation.Vertical) {
      contents += new Label("--- above ---")
      
      contents += new TextComponent {
        override lazy val peer: JTextPane = new JTextPane() with SuperMixin {

          setEditorKit(new StyledEditorKit())

          SwingKit.executeLater {
            val document = peer.getDocument
            val kit = peer.getEditorKit
            val reader = new StringReader("Hi there!")

            kit.read(reader, document, 0)
          }
        }
      }

      contents += new Label("--- below ---")
    }
  }
}