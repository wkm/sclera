package sclera.ux.editor

import swing._
import java.io.StringReader
import sclera.util.SwingKit
import javax.swing.text.{StyledEditorKit, DefaultEditorKit}
import java.awt.BorderLayout
import javax.swing.{JScrollPane, Box, JPanel, JTextPane}

/**
 * sclera.ux.editor.VerticalCompactTest
 * wiktor - 2011
 */

object VerticalCompactTest extends SimpleSwingApplication {
  def top = new MainFrame {
    contents = new ScrollPane(new Panel with SequentialContainer.Wrapper {
      lazy val box = new javax.swing.Box(Orientation.Vertical.id)

      override lazy val peer = {
//        val scroller = new javax.swing.JScrollPane(box)

        val panel = new javax.swing.JPanel with SuperMixin
        panel.setLayout(new BorderLayout)
        panel.add(box, BorderLayout.NORTH)
        panel
      }

      def add(component: Component) {
        box.add(component.peer)
      }

      add( new Label("--- above ---") )
      
      add( new TextComponent {
        override lazy val peer: JTextPane = new JTextPane() with SuperMixin {

          setEditorKit(new StyledEditorKit())

          SwingKit.executeLater {
            val document = peer.getDocument
            val kit = peer.getEditorKit
            val reader = new StringReader("Hi there!")

            kit.read(reader, document, 0)
          }
        }
      } )

      add( new Label("--- below ---") )
    })
  }
}