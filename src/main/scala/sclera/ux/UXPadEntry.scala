package sclera.ux

import editor.UXEditorComponent
import formatting._
import java.io.StringReader
import swing.Component
import collection.mutable.Buffer
import sclerakit.ux.RenderDispatch

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
  val editor = new UXEditorComponent(this)
  set(editor)

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