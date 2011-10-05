package sclera.ux

import editor.UXEditorComponent
import swing.{Orientation, Label, BoxPanel, Component}

/**
 * A representation of an individual entry in a Sclera pad
 */
class UXPadEntry(
    var in: String,
    var out: String = "output"
) extends UXPadEntryGroup {
  contents += new UXEditorComponent()
  contents += new UXTextComponent(text = "Out: "+out)
}