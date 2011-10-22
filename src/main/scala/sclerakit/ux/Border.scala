package sclerakit.ux

import javax.swing.{Box, BorderFactory}
import sclera.format.color.SolarizedColorPalette
import swing.{Component, Orientation}
import sclera.ux.ext.RoundedBorder
import sclera.ux.{BoxCornerValues, BoxSideValues, FrameStyle}
import sclera.ux.formatting.{BaseBorderFormatting, FrameGenerator, BaseFormatting}

/**
 * sclerakit.ux.Border
 * wiktor - 2011
 */

case class Border (
    val obj: Any,
    val style: FrameStyle = BaseBorderFormatting
)
  extends WillRender
{
  def render = {
    val border = FrameGenerator.generate(style)
    val layout = new Box(Orientation.Vertical.id)
    layout.setBorder(border)
    layout.add(RenderDispatch(obj).render.peer)

    new Component {
      override lazy val peer = layout
    }
  }
}