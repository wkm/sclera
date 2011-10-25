package sclera.ux.formatting

import javax.swing.border.Border
import javax.swing.BorderFactory
import sclera.util.Loggable
import sclera.ux.BoxCornerValues._
import sclera.ux.ext.RoundedBorder
import sclera.ux.{BoxCornerValues, BoxSideValues, FrameStyle}
import sclera.format.color.{DefaultColorPalette, SolarizedColorPalette}

/**
 * sclera.ux.formatting.FrameGenerator
 * wiktor - 2011
 */

/**
 * Factory for creating swing Border objects based on a FrameStyle
 * specification
 */
object FrameGenerator
extends Loggable
{
  def generate(style: FrameStyle) : Border = {
    val color = style.frameColor.getOrElse(DefaultColorPalette("black"))
    val thickness = style.frameThickness.getOrElse(BoxSideValues(1))
    val padding = style.framePadding.getOrElse(BoxSideValues(0))
    val rounding = style.frameRounding.getOrElse(BoxCornerValues(0))
    val showFrameCorners = style.showFrameCorners.getOrElse(BoxCornerValues(true))
    val showFrameSides = style.showFrameSides.getOrElse(BoxSideValues(true))

    val cornerThickness = BoxCornerValues(
      (thickness.left+thickness.top)/2,
      (thickness.top+thickness.right)/2,
      (thickness.right+thickness.bottom)/2,
      (thickness.bottom+thickness.left)/2
    )

    new RoundedBorder(
      BoxSideValues(color),
      BoxCornerValues(color),
      thickness,
      cornerThickness,
      rounding,
      showFrameSides,
      showFrameCorners,
      padding
    )
  }
}