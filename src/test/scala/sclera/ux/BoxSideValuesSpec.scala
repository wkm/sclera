package sclera.ux

import org.specs._

/**
 * sclera.ux.BoxSideValuesSpec
 * wiktor - 2011
 */

object BoxSideValuesSpec extends Specification {
  "BoxSideValues" should {
    "equal-sided" in {
      val sides = BoxSideValues[Int](12)

      sides.top must_== 12
      sides.right must_== 12
      sides.bottom must_== 12
      sides.left must_== 12

      sides.equalHorizontals mustBe true
      sides.equalVerticals mustBe true
      sides.equalSides mustBe true
    }

    "verticals and horizontals" in {
      val sides = BoxSideValues[Int](5, 7)
      sides.top must_== 5
      sides.bottom must_== 5

      sides.left must_== 7
      sides.right must_== 7

      sides.equalHorizontals mustBe true
      sides.equalVerticals mustBe true
      sides.equalSides mustBe false
    }

    "each side different" in {
      val sides = BoxSideValues[Int](3, 5, 7, 9)
      sides.top must_== 3
      sides.right must_== 5
      sides.bottom must_== 7
      sides.left must_== 9

      sides.equalHorizontals mustBe false
      sides.equalVerticals mustBe false
      sides.equalSides mustBe false
    }
  }
}