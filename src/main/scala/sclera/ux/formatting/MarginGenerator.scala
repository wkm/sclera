package sclera.ux.formatting

import sclera.util.Loggable
import javax.swing.border.Border
import sclera.ux.{MarginStyle, FrameStyle}
import javax.swing.BorderFactory

/**
 * sclera.ux.formatting.MarginGenerator
 * wiktor - 2011
 */

object MarginGenerator
extends Loggable
{
  def generate(style: MarginStyle) = {
    val thickness = style.marginWidth
    BorderFactory.createEmptyBorder(
      thickness.top,
      thickness.left,
      thickness.bottom,
      thickness.right
    )
  }
}