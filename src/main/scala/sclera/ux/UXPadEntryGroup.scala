package sclera.ux

import swing._
import java.awt.Color


class UXPadEntryGroup (
    val orientation: Orientation.Value = Orientation.Vertical
) extends BoxPanel(orientation = orientation) {
  xLayoutAlignment = 0
  yLayoutAlignment = 0.5

  background = Color.WHITE
}