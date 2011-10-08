package sclera.ux.wrappers

import swing.UIElement
import javax.swing.{JLabel, JTextPane}
import java.io.StringReader

/**
 * sclera.ux.wrappers.JNestedTextComponent
 * wiktor - 2011
 *
 * A component built basically to vertically layout
 * TextPaneComponents which are as vertically compact as possible
 */
class JNestedTextComponent extends JTextPane {
  setEditable(false)

  override def add(component: java.awt.Component) = {
    insertComponent(component)
    getEditorKit.read(new StringReader("\n"), getDocument, getDocument.getLength)

    component
  }
}