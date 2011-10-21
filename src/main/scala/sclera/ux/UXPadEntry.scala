package sclera.ux

import editor.UXEditorComponent
import java.io.StringReader

/**
 * A representation of an individual input/output pair in a Sclera pad
 */
abstract class UXPadEntry (
    val content: UXObjectComponent
)
  extends UXPadEntryGroup
{
  if(content != null)
    contents += content
  
  def willEvaluate = false;
};

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
  extends UXPadEntry(null)
  with EntryEvaluates
{
  val editor = new UXEditorComponent(this)
  contents += editor

  def entryContents =
    Some(editor.textContent)
}

/**
 * An "output" entry: a highly formatted, nested output type
 */
class UXPadOutputEntry (
    val text: String
)
  extends UXPadEntry(new UXTextComponent("Out: "+text));