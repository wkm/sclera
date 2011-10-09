package sclera.ux

import editor.UXEditorComponent
import javax.swing.Box
import swing._
import sclera.format.color.SolarizedColorPalette
import java.io.StringReader

/**
 * A representation of an individual input/output pair in a Sclera pad
 */
class UXPadEntry(
  val content: String = "",
  val isInput: Boolean = false
) extends UXPadEntryGroup {
  
  if(isInput) {
    val editor = new UXEditorComponent()
    editor.editorKit.read(new StringReader(content), editor.document, editor.document.getLength)
    contents += editor
  } else
    contents += new UXTextComponent(text = "Out: "+content)
};