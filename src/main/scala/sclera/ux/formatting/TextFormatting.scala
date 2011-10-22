package sclera.ux.formatting

import java.awt.Color
import sclera.format.color.SolarizedColorPalette
import sclera.ux.{BoxCornerValues, BoxSideValues, UXPadEntryStyle}

/**
 * sclera.ux.formatting.TextFormatting
 * wiktor - 2011
 */

class BaseFormatting
extends UXPadEntryStyle {
  var fontFamily = "Helvetica"
  var fontSize = 11

  var frameColor: Option[Color] = None
  var frameThickness: Option[BoxSideValues[Int]] = None
  var framePadding: Option[BoxSideValues[Int]] = None
  var frameRounding: Option[BoxCornerValues[Int]] = None

  var marginWidth = BoxSideValues[Int](5, 10, 2, 10)
}
object BaseFormatting extends BaseFormatting

class InputFormatting
extends BaseFormatting {
  fontFamily = "Menlo"
  fontSize = 12

  marginWidth = BoxSideValues[Int](20, 32, 0, 32)
  frameColor = Some(SolarizedColorPalette("base2"))
  frameThickness = Some(BoxSideValues[Int](1, 0))
  framePadding = Some(BoxSideValues[Int](2, 0))
}
object InputFormatting extends InputFormatting

class OutputFormatting
extends BaseFormatting {
  marginWidth = BoxSideValues[Int](0, 32, 2, 32)
}
object OutputFormatting extends OutputFormatting