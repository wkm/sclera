package sclera.algo

import org.specs.Specification
import collection.immutable.WrappedString

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

  "SplittableIntervalMapping" should {
    "start with an empty mapping" in {
      SplittableIntervalMapping[String].intervals must haveSize(0)
    }

    "have the initial interval" in {
      val mapping = SplittableIntervalMapping[String]
      mapping.addInterval((1, 10), "A")

      mapping.intervals must haveSize(1)
      mapping.intervals must haveTheSameElementsAs(
        List(
          (Interval(1, 10), "A")
        )
      )
    }

    "accept new intervals" in {
      val mapping = SplittableIntervalMapping[String]
      mapping.addInterval((20, 30), "A")
      mapping.addInterval((40, 50), "B")

      mapping.intervals must haveSize(2)
      mapping.intervals must haveTheSameElementsAs(
        List(
          (Interval(20, 30), "A"),
          (Interval(40, 50), "B")
        )
      )

      mapping.addInterval((0, 10), "C")
      mapping.intervals must haveSize(3)
      mapping.intervals must haveTheSameElementsAs(
        List(
          (Interval(0, 10), "C"),
          (Interval(20, 30), "A"),
          (Interval(40, 50), "B")
        )
      )
    }
  }
}