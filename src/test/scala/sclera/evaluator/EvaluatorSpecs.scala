package sclera.evaluator

import org.specs._

/**
 * sclera.evaluator.EvaluatorSpec
 * wiktor - 2011
 */

object EvaluatorSpecs extends Specification {
  "ScleraEvaluator" should {
    "simple math" in {
      Evaluator.Processor !? Evaluator.Evaluate("1") must_==(Evaluator.Result(1))
      Evaluator.Processor !? Evaluator.Evaluate("1+2") must_==(Evaluator.Result(3))
      Evaluator.Processor !? Evaluator.Evaluate("12 + 13") must_==(Evaluator.Result(25))
    }

    "set values" in {
      Evaluator.Processor !? Evaluator.Evaluate("val a = 12") must_==(Evaluator.Result(12))
      Evaluator.Processor !? Evaluator.Evaluate("a") must_==(Evaluator.Result(12))
    }
  }
}