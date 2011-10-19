package sclera.ux

import swing._
import java.awt.Color
import sclera.util.Loggable


class UXPadEntryGroup (
    val orientation: Orientation.Value = Orientation.Vertical
) extends BoxPanel(orientation = orientation)
  with Loggable {
  xLayoutAlignment = 0
  yLayoutAlignment = 0.5

  background = Color.WHITE
}