package sclera.ux.formatting

import org.specs._
import sclera.ux.{BoxSideValues, FrameStyle}
import java.awt.{Insets, Color}
import sclera.format.color.SolarizedColorPalette
import javax.swing.border.{MatteBorder, LineBorder, EmptyBorder}

/**
 * sclera.ux.formatting.FrameGeneratorSpecs
 * wiktor - 2011
 */
object FrameGeneratorSpecs extends Specification {
  "FrameGenerator" should {
    "create empty borders" in {
      val border = FrameGenerator.generate(new FrameStyle {
        var frameColor: Option[Color] = None
        var frameThickness: Option[BoxSideValues[Int]] = None
        var framePadding: Option[BoxSideValues[Int]] = None
      })

      border match {
        case e: EmptyBorder =>
          val insets = e.getBorderInsets
          insets.left must_== 0
          insets.top must_== 0
          insets.bottom must_== 0
          insets.right must_== 0

        case _ =>
          fail("not an EmptyBorder")
      }
    }

    "create pure padding" in {
      val border = FrameGenerator.generate(new FrameStyle {
        var frameColor: Option[Color] = None
        var frameThickness: Option[BoxSideValues[Int]] = None
        var framePadding : Option[BoxSideValues[Int]] =
          Some(BoxSideValues[Int](5))
      })

      border match {
        case e: EmptyBorder =>
          val insets = e.getBorderInsets
          insets.left must_== 5
          insets.top must_== 5
          insets.bottom must_== 5
          insets.right must_== 5

        case _ =>
          fail("not an EmptyBorder")
      }
    }

    "create simple line border" in {
      val border = FrameGenerator.generate(new FrameStyle {
        var frameColor: Option[Color] = Some(SolarizedColorPalette("base3"))
        var frameThickness: Option[BoxSideValues[Int]] = Some(BoxSideValues[Int](5))
        var framePadding : Option[BoxSideValues[Int]] = None
      })

      border match {
        case e: LineBorder =>
          e.getLineColor mustBe SolarizedColorPalette("base3")
          e.getThickness mustBe 5

        case _ =>
          fail("not a LineBorder: "+border)
      }
    }

    "create complicated line border" in {
      val border = FrameGenerator.generate(new FrameStyle {
        var frameColor: Option[Color] = Some(SolarizedColorPalette("base3"))
        var frameThickness: Option[BoxSideValues[Int]] = Some(BoxSideValues[Int](1, 2, 3, 4))
        var framePadding : Option[BoxSideValues[Int]] = None
      })

      border match {
        case m: MatteBorder =>
          m.getMatteColor mustBe SolarizedColorPalette("base3")
          val insets = m.getBorderInsets
          insets.left mustBe 4
          insets.top mustBe 1
          insets.bottom mustBe 3
          insets.right mustBe 2

        case _ =>
          fail("not a MatteBorder: "+border)
      }
    }
  }
}