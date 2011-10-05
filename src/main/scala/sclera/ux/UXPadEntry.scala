package sclera.ux

import swing.{Orientation, Label, BoxPanel, Component}

/**
 * A representation of an individual entry in a Sclera pad
 */
class UXPadEntry(
    var in: String,
    var out: String = "output",
    val orientation: Orientation.Value = Orientation.Vertical
) extends BoxPanel(orientation = orientation) {
  contents += new Label("In: a")
  contents += new Label("Out: b")
}