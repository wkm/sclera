package sclera.ux

import swing.{Orientation, Label, BoxPanel, Component}

/**
 * A representation of an individual entry in a Sclera pad
 */
class UXPadEntry(
    var in: String,
    var out: String = "output"
) extends UXPadEntryGroup {
  contents += new Label("In: " + in)
  contents += new Label("Out: " + out)
}