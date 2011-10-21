package sclera.ux.formatting

import javax.swing.border.Border
import javax.swing.BorderFactory
import sclera.ux.{BoxSideValues, FrameStyle}
import sclera.util.Loggable

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
    var padding:Option[Border] = None
    if(style.framePadding.isDefined)
      padding = Some(generatePaddingBorder(style.framePadding.get))

    var outside:Option[Border] = None
    if(style.frameColor.isDefined)
      outside = Some(generateOutsideBorder(style))

    if(padding.isDefined && outside.isDefined)
      return BorderFactory.createCompoundBorder(outside.get, padding.get)

    if(padding.isDefined)
      return padding.get

    if(outside.isDefined)
      return outside.get
    else
      return BorderFactory.createEmptyBorder()
  }

   private def generatePaddingBorder(thickness: BoxSideValues[Int]) =
     BorderFactory.createEmptyBorder(
       thickness.top,
       thickness.left,
       thickness.bottom,
       thickness.right
     )

   private def generateOutsideBorder(style: FrameStyle) = {
     val thickness = style.frameThickness.get

     if(thickness.equalSided) {
       BorderFactory.createLineBorder(
         style.frameColor.get,
         thickness.top
       )
     } else
       BorderFactory.createMatteBorder(
         thickness.top,
         thickness.left,
         thickness.bottom,
         thickness.right,
         style.frameColor.get
       )
   }
}