package sclera.ux

import editor.{LineNumbering, UXEditorComponent}
import formatting._
import java.io.StringReader
import collection.mutable.Buffer
import sclerakit.ux.RenderDispatch
import swing.{ScrollPane, Component}
import javax.swing.event.{DocumentEvent, DocumentListener}
import javax.swing.{ScrollPaneConstants, JScrollPane, BorderFactory}
import java.awt.event._

/**
 * A representation of an individual input/output pair in a Sclera pad
 */
abstract class UXPadEntry (
    val entryValue: UXComponent,
    val style: UXPadEntryStyle = BaseFormatting
)
  extends UXPadEntryGroup
{
  if(entryValue != null)
    set(entryValue)

  def set(component: Component) {
    val container = FormattingContainerGenerator.generate(style)
    container.add(component.peer)
    peer.add(container)
  }
  
  def willEvaluate = false;

  def createContainer =
    FrameGenerator.generate(style)
  
  def listener() = {
    peer.addFocusListener(new FocusListener {
      def focusGained(p1: FocusEvent) {

      }

      def focusLost(p1: FocusEvent) {}
    })
  }
};

object UXPadEntry {
  def apply(value: Any) =
    new UXPadEntry(null) {
      set(RenderDispatch(value).render)
    }
}

trait EntryEvaluates
  extends UXPadEntry
{
  override
  def willEvaluate = true;

  def entryContents: Option[String];
}

/**
 * An "input" entry: a structured text editor for inputting scala
 */
class UXPadInputEntry (
    val pad: UXPad
)
  extends UXPadEntry(null, InputFormatting)
  with EntryEvaluates
{
  val editor = new UXEditorComponent(this);
  val scrollPane: Component = new Component {
    override lazy val peer = new JScrollPane(editor.peer) {
      setBorder(BorderFactory.createEmptyBorder())
      setRowHeaderView(new LineNumbering(UXPadInputEntry.this.peer, editor.peer))
      setWheelScrollingEnabled(false)
      setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER)
      setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER)
    }
  }

  editor.peer.addComponentListener(new ComponentAdapter() {
    override
    def componentResized(e: ComponentEvent) {
      pad.peer.invalidate()
      editor.peer.revalidate()
      pad.peer.validate()
    }
  })

  editor.peer.getDocument.addDocumentListener(new DocumentListener {
    def changedUpdate(e: DocumentEvent) {}

    def removeUpdate(e: DocumentEvent) {
      pad.peer.invalidate()
      editor.peer.revalidate()
      pad.peer.validate()
    }

    def insertUpdate(e: DocumentEvent) {}
  })

  set(scrollPane)

  def entryContents =
    Some(editor.textContent)
}

/**
 * An "output" entry: a highly formatted, nested output type
 */
class UXPadOutputEntry (
    val value: Any
)
  extends UXPadEntry(null, OutputFormatting)
{
  val willRender = RenderDispatch(value)
  val rendered = willRender.render
  set(rendered)
}