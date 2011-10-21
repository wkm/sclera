package sclera.ux.formatting

import sclera.ux.{BoxSideValues, UXPadEntryStyle}
import java.awt.Color

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

  var marginWidth = BoxSideValues[Int](5, 10, 2, 10)
}

class InputFormatting
extends BaseFormatting {
  var fontFamily = "Menlo"
  var fontSize = 12

  var marginWidth = BoxSideValues[Int](10, 10, 0, 10)
}

class OutputFormatting
extends BaseFormatting {
  var marginWidth = BoxSideValues[Int](0, 10, 2, 10)
}