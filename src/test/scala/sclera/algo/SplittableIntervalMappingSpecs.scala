package sclera.algo

import org.specs.Specification

/**
 * sclera.algo.SplittableIntervalMappingSpecs
 * wiktor - 2011
 */

class SplittableIntervalMappingSpecs extends Specification {
  "Interval" should {
    "not allow malformed intervals" in {
      Interval(2, 3) must not(throwA[Exception])
      Interval(2, 2) must not(throwA[Exception])
      Interval(3, 2) must throwA[MalformedIntervalException]
    }

    "implement ==" in {
      Interval(1,2) == Interval(1,2) mustBe true
      Interval(0, 100) == Interval(0, 100) mustBe true

      Interval(1,3) == Interval(1,4) mustBe false
      Interval(2,3) == Interval(1,3) mustBe false
    }

    "implement <" in {
      Interval(1,2) < Interval(3,4) mustBe true

      Interval(3,4) < Interval(1,2) mustBe false
      Interval(1,2) < Interval(2,3) mustBe false
    }

    "implement <=" in {
      Interval(1,2) < Interval(3,4) mustBe true
      Interval(1,2) < Interval(2,3) mustBe false

      Interval(3,4) < Interval(1,2) mustBe false
    }

    "implement >" in {
      Interval(3,4) > Interval(1,2) mustBe true

      Interval(1,2) > Interval(3,4) mustBe false
      Interval(1,2) > Interval(2,3) mustBe false
    }

    "implement >=" in {
      Interval(3,4) >= Interval(1,2) mustBe true
      Interval(2,4) >= Interval(1,3) mustBe true

      Interval(1,2) > Interval(3,4) mustBe false
    }
  }
}