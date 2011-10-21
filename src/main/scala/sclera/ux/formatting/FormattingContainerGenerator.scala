package sclera.ux.formatting

import sclera.ux.UXPadEntryStyle
import swing.Orientation
import javax.swing.{BorderFactory, Box, BoxLayout}

/**
 * sclera.ux.formatting.FormattingContainerGenerator
 * wiktor - 2011
 */

object FormattingContainerGenerator {
  def generate(style: UXPadEntryStyle) = {
    // get each individual border
    val margin = MarginGenerator.generate(style)
    val frame = FrameGenerator.generate(style)

    // stack them together
    val border = BorderFactory.createCompoundBorder(margin, frame)
    val layout = new Box(Orientation.Vertical.id)
    layout.setBorder(border)

    layout
  }
}