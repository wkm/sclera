package sclera.ux.formatting

import sclera.ux.UXPadEntryStyle
import javax.swing.{Box, BoxLayout}
import swing.Orientation

/**
 * sclera.ux.formatting.FormattingContainerGenerator
 * wiktor - 2011
 */

object FormattingContainerGenerator {
  def generate(style: UXPadEntryStyle) = {
    val frame = FrameGenerator.generate(style)
    val layout = new Box(Orientation.Vertical.id)
    layout.setBorder(frame)

    layout
  }
}