package sclera.ux

import editor.UXEditorComponent
import java.io.StringReader

/**
 * A representation of an individual input/output pair in a Sclera pad
 */
class UXPadEntry(
  val pad: UXPad,
  val content: String = "",
  val isInput: Boolean = false
) extends UXPadEntryGroup {

  private var editorComponent : Option[UXEditorComponent] = None

  if(isInput) {
    val editor = new UXEditorComponent(this)
    editor.editorKit.read(new StringReader(content), editor.document, editor.document.getLength)

    contents += editor
    editorComponent = Some(editor)
  } else {
    contents += new UXTextComponent(text = "Out: "+content)
  }

  def entryContents =
    if(editorComponent.isDefined)
      Some(editorComponent.get.textContent)
    else
      None
};