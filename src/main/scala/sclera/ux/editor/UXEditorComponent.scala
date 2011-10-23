
package sclera.ux.editor

import javax.swing.text._
import collection.immutable.TreeMap
import sclera.ux.wrappers.TextPaneComponent
import sclera.format.color.SolarizedColorPalette
import java.awt.event.{FocusEvent, FocusListener}
import java.io.StringWriter
import sclera.util.{Loggable, SwingKit}
import java.awt.{Dimension, Font, Graphics}
import swing.event.{UIElementResized, Key, KeyTyped}
import sclera.ux.{UXPadInputEntry, UXComponent, UXPadEntry, UX}
import reflect.BeanProperty
import java.nio.channels.ServerSocketChannel

class UXEditorComponent (
    val padEntry: UXPadInputEntry
)
  extends TextPaneComponent
  with UXComponent
  with Loggable
{
  /**
   * extract the text entryValue from the text pane fragment
   */
  def textContent: String = {
    val kit = editorKit
    val stringWriter = new StringWriter()
    kit.write(stringWriter, document, 0, document.getLength)
    return stringWriter.toString
  }

  SwingKit.executeLater {
    editorKit = new ScalaEditorKit()
    contentType = "text/scala"
    font = new Font("Cousine", Font.BOLD, 12)
    foreground = SolarizedColorPalette("black")

    listenTo(keys)
    reactions += {
      // evaluation
      case KeyTyped(_, text, Key.Modifier.Shift, _) if text.toInt == 13 =>
        UX.Processor ! UX.Evaluate()
    }

    peer.addFocusListener(new FocusListener {
      def focusGained(e: FocusEvent) {
        padEntry.pad.processor !? UX.ChangeEntryFocus(padEntry)
      }

      def focusLost(e: FocusEvent) {
        padEntry.pad.processor !? UX.LoseEntryFocus(padEntry)
      }
    })
  }
}

class ScalaEditorKit (
    val viewFactory: ScalaViewFactory = new ScalaViewFactory(),
    val contentType: String  = "text/scala"
)
  extends StyledEditorKit
{
  // cannot use @BeanProperty because we need `override`
  override def getViewFactory = viewFactory
  override def getContentType = contentType
}

class ScalaViewFactory
  extends ViewFactory
{
  override def create(element: Element) =
    new ScalaView(element)
}

class ScalaView(val element: Element)
  extends PlainView(element)
{
  getDocument.putProperty(PlainDocument.tabSizeAttribute, 2)

  override protected
  def drawUnselectedText(graphics: Graphics, x: Int, y: Int, p0: Int, p1: Int): Int = {
    val doc = getDocument
    val text = doc.getText(p0, p1 - p0)
    var finalX = x
    val segment = getLineBuffer

    def drawFragment(start: Int, stop: Int, segment: Segment) {
      if(start == stop) return;

      doc.getText(p0 + start, stop-start, segment)
      println("Text: "+(start, stop)+" => "+doc.getText(p0 + start, stop-start))
      finalX = Utilities.drawTabbedText(segment, finalX, y, graphics, this, start)
    }

    var lastIndex = 0
    for(fragment <- ScalaSourceHighlighter.highlight(text)) {
      graphics.setColor(colorForComponent(ScalaSourceComponent.Plain))
      drawFragment(lastIndex, fragment.start, segment)

      graphics.setColor(colorForComponent(fragment.component))
      drawFragment(fragment.start, fragment.end, segment)

      lastIndex = fragment.end
    }

    if(lastIndex != text.length) {
      graphics.setColor(colorForComponent(ScalaSourceComponent.Plain))
      drawFragment(lastIndex, text.length, segment)
    }

    return finalX
  }

  private def colorForComponent(component: ScalaSourceComponent.ScalaSourceComponent) = {
    import ScalaSourceComponent._
    SolarizedColorPalette(component match {
      case Plain => "black"
      case Keyword => "orange"
      case String => "green"
      case Comment => "base02"
    })
  }
}