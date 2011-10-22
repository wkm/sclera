package sclerakit.ux

import javax.swing.{Box, BorderFactory}
import sclera.format.color.SolarizedColorPalette
import swing.{Component, Orientation}
import sclera.ux.ext.RoundedBorder
import sclera.ux.{BoxCornerValues, BoxSideValues, FrameStyle}

/**
 * sclerakit.ux.Border
 * wiktor - 2011
 */

case class Border (
    val obj: Any,
    val settings: FrameStyle = null
)
  extends WillRender
{
  def render = {
    val border = new RoundedBorder(
      BoxSideValues(SolarizedColorPalette("blue")),
      BoxCornerValues(SolarizedColorPalette("blue")),
      BoxSideValues(2),
      BoxCornerValues(2),
      BoxCornerValues(5, 30, 8, 15),
      BoxSideValues(true),
      BoxCornerValues(true),
      BoxSideValues(3)
    )
    
    val layout = new Box(Orientation.Vertical.id)
    layout.setBorder(border)
    layout.add(RenderDispatch(obj).render.peer)

    new Component {
      override lazy val peer = layout
    }
  }
}