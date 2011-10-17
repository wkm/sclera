package sclera.evaluator

import org.specs._

/**
 * sclera.evaluator.InterpreterWrapperSpecs
 * wiktor - 2011
 */

object InterpreterWrapperSpecs extends Specification {
  "InterpreterWrapper" should {
    "Simple evaluation" in {
      skip("for now")
      val wrapper = new InterpreterWrapper

      wrapper.interpret("println(\"hello there little pumpkin\"); 12")
      wrapper.interpret("println(\"res0 = \"+res0); 13")
      wrapper.interpret("println(\"weirdo ==\"+$line2.$read.$iw.$iw.`res0`)")
      wrapper.interpret("1") must_== 1
      wrapper.interpret("1+2") must_== 3
      wrapper.interpret("12 + 13") must_== 15
    }

    "Set values" in {
      skip("getting the stupid interpreter to work")
      val wrapper = new InterpreterWrapper

      wrapper.interpret("val a = 12") must_== 12
      wrapper.interpret("a") must_== 12
    }
  }
}