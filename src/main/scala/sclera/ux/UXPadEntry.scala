package sclera.ux

import editor.UXEditorComponent
import javax.swing.Box
import swing._

/**
 * A representation of an individual input/output pair in a Sclera pad
 */
class UXPadEntry(
    var in: String,
    var out: String = "output"
) extends UXPadEntryGroup {
  contents += new UXEditorComponent()
  contents += new UXTextComponent(text = "Out: "+out)
}