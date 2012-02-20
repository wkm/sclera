package sclera.ux.formatting

import java.awt.Color
import sclera.format.color.DefaultColorPalette
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
  var showFrameCorners: Option[BoxCornerValues[Boolean]] = Some(BoxCornerValues(false))
  var showFrameSides: Option[BoxSideValues[Boolean]] = Some(BoxSideValues(false))

  var marginWidth = BoxSideValues[Int](5, 10, 2, 10)
}
object BaseFormatting extends BaseFormatting

class InputFormatting
extends BaseFormatting {
  fontFamily = "Menlo"
  fontSize = 12

  marginWidth = BoxSideValues[Int](20, 32, 0, 32)
  frameColor = Some(DefaultColorPalette("base1"))
  frameThickness = Some(BoxSideValues[Int](1))
  framePadding = None
  frameRounding = Some(BoxCornerValues(3, 0))
  showFrameSides = Some(BoxSideValues(false, false, false, true))
  showFrameCorners = Some(BoxCornerValues(true, false, false, true))
}
object InputFormatting extends InputFormatting

object ActiveInputFormatting extends InputFormatting {
  frameThickness = Some(BoxSideValues[Int](3))
}


class OutputFormatting
extends BaseFormatting {
  marginWidth = BoxSideValues[Int](0, 32, 2, 32)
}
object OutputFormatting extends OutputFormatting


class BaseBorderFormatting
  extends BaseFormatting
{
  showFrameSides = Some(BoxSideValues(true))
  showFrameCorners = Some(BoxCornerValues(true))
  frameRounding = Some(BoxCornerValues(2))
}
object BaseBorderFormatting extends BaseBorderFormatting


class LineNumberFormatting
  extends BaseFormatting
{
  frameColor = Some(DefaultColorPalette("base2"))
  showFrameSides = Some(BoxSideValues(false, true, false, false))
  showFrameCorners = Some(BoxCornerValues(false))
  frameRounding = Some(BoxCornerValues(0))
}
object LineNumberFormatting extends LineNumberFormatting