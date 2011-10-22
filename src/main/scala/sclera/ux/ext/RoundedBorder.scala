package sclera.ux.ext

import javax.swing.border.AbstractBorder
import sclera.ux.{BoxCornerValues, BoxSideValues}
import java.awt._

/**
 * sclera.ux.ext.RoundedBorder
 * wiktor - 2011
 */

class RoundedBorder(
    val color : BoxSideValues[Color],
    val cornerColors : BoxCornerValues[Color],
    val borderThickness: BoxSideValues[Int],
    val cornerThickness: BoxCornerValues[Int],
    val rounding: BoxCornerValues[Int],

    val enabledBorders: BoxSideValues[Boolean] = BoxSideValues(true),
    val enabledCorners: BoxCornerValues[Boolean] = BoxCornerValues(true),
    val padding: BoxSideValues[Int] = BoxSideValues(0)
)
  extends AbstractBorder
{

  override
  def paintBorder(
      c: Component,
      g: Graphics,
      x: Int, y: Int,
      w: Int, h: Int
  ) =
    paintBorder(c, g.asInstanceOf[Graphics2D], x,y, w,h)

  def stroke(width: Int) =
    new BasicStroke(width, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND)

  def paintBorder(
      c: Component,
      g: Graphics2D,
      x: Int, y: Int,
      w: Int, h: Int
  ) {
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)

    // UPPER LEFT
    if(enabledCorners.upperLeft) {
      g.setColor(cornerColors.upperLeft)
      g.setStroke(new BasicStroke(cornerThickness.upperLeft))
      g.drawArc(
        x, y,
        2*rounding.upperLeft, 2*rounding.upperLeft,
        180, -90
      )
    }

    // TOP
    if(enabledBorders.top) {
      g.setColor(color.top)
      g.setStroke(stroke(borderThickness.top))
      g.drawLine(
        x+rounding.upperLeft,y,
        x+w-rounding.upperRight,y
      )
    }

    // UPPER RIGHT
    if(enabledCorners.upperRight) {
      g.setColor(cornerColors.upperRight)
      g.setStroke(stroke(cornerThickness.upperRight))
      g.drawArc(
        x+w - 2*rounding.upperRight - 1, y,
        2*rounding.upperRight, 2*rounding.upperRight,
        90, -90
      )
    }

    // RIGHT
    if(enabledBorders.right) {
      g.setColor(color.right)
      g.setStroke(stroke(borderThickness.right))
      g.drawLine(
        x+w-(borderThickness.right/2.0f).round,y+rounding.upperRight,
        x+w-(borderThickness.right/2.0f).round,y+h-rounding.lowerRight
      )
    }

    // LOWER RIGHT
    if(enabledCorners.lowerRight) {
      g.setColor(cornerColors.lowerRight)
      g.setStroke(stroke(cornerThickness.lowerRight))
      g.drawArc(
        x+w - 2*rounding.lowerRight - (borderThickness.right/2.0f).round, y+h - 2*rounding.lowerRight-(borderThickness.bottom/2.0f).round,
        2*rounding.lowerRight, 2*rounding.lowerRight,
        0, -90
      )
    }

    // BOTTOM
    if(enabledBorders.bottom) {
      g.setColor(color.bottom)
      g.setStroke(stroke(borderThickness.bottom))
      g.drawLine(
        x+rounding.lowerLeft,y+h-(borderThickness.bottom/2.0f).round,
        x+w-rounding.lowerRight,y+h-(borderThickness.bottom/2.0f).round
      )
    }

    // LOWER LEFT
    if(enabledCorners.lowerLeft) {
      g.setColor(cornerColors.lowerLeft)
      g.setStroke(stroke(cornerThickness.lowerLeft))
      g.drawArc(
        x, y+h - 2*rounding.lowerLeft - 1,
        2*rounding.lowerLeft, 2*rounding.lowerLeft,
        -90, -90
      )
    }

    // LEFT
    if(enabledBorders.left) {
      g.setColor(color.left)
      g.setStroke(stroke(borderThickness.left))
      g.drawLine(
        x,y+rounding.upperLeft,
        x,y+h-rounding.lowerLeft
      )
    }
    
  }

  def computeInsets =
    BoxSideValues(
      padding.top + borderThickness.top + 5,
      padding.right + borderThickness.right + 5,
      padding.bottom + borderThickness.bottom + 5,
      padding.left + borderThickness.left + 5
    )

  override
  def getBorderInsets(c: Component) = {
    val sides = computeInsets
    new Insets(sides.top, sides.left, sides.bottom, sides.right)
  }

  override
  def getBorderInsets(c: Component, insets: Insets) = {
    val sides = computeInsets
    insets.set(sides.top, sides.left, sides.bottom, sides.right)
    insets
  }

  override
  def isBorderOpaque = true
}