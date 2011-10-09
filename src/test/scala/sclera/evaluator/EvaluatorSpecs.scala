package sclera.evaluator

import org.specs._

/**
 * sclera.evaluator.EvaluatorSpec
 * wiktor - 2011
 */

object EvaluatorSpecs extends Specification {
  "ScleraEvaluator" should {
    "simple math" in {
      skip("evaluators need to work first")
      Evaluator.Processor !? Evaluator.Evaluate("1")
      Evaluator.Processor !? Evaluator.Evaluate("1+2")
      Evaluator.Processor !? Evaluator.Evaluate("12 + 13")
    }

    "set values" in {
      skip("evaluators need to work first")
      Evaluator.Processor !? Evaluator.Evaluate("val a = 12")
      Evaluator.Processor !? Evaluator.Evaluate("a")
    }
  }
}