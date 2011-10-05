package sclera.ux

import swing.{Orientation, Label, BoxPanel, Component}

/**
 * A representation of an individual entry in a Sclera pad
 */
class UXPadEntry(
    var in: String,
    var out: String = "output"
) extends UXPadEntryGroup {
  contents += new UXTextComponent(text = "In: "+in)
  contents += new UXTextComponent(text = "Out: "+out)
}