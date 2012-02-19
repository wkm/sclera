package sclera.evaluator

import org.specs._
import tools.nsc.interpreter.IR
import java.lang.Integer

/**
 * sclera.evaluator.InterpreterWrapperSpecs
 * wiktor - 2011
 */
object InterpreterWrapperSpecs extends Specification {
  "InterpreterWrapper" should {
    "Simple evaluation" in {
      val wrapper = new InterpreterWrapper

      wrapper.interpret("1") must be like {
        case InterpretedResult(IR.Success, value) =>
          value == 1
      }

      wrapper.interpret("1+2") must be like {
        case InterpretedResult(IR.Success, value) =>
          value == 3
      }
      
      wrapper.interpret("12 + 13") must be like {
        case InterpretedResult(IR.Success, value) =>
          value == 25
      }
    }

    "Set values" in {
      val wrapper = new InterpreterWrapper

      wrapper.interpret("val a = 12") must be like {
        case InterpretedResult(IR.Success, value) =>
          value == 12
      }
      wrapper.interpret("a") must be like {
        case InterpretedResult(IR.Success, value) =>
          value == 12
      }
    }

    "Define methods" in {
      val wrapper = new InterpreterWrapper
      wrapper.interpret("def foo(x:Int) = x+1") must be like {
        case v => println(v); true
      }
    }
  }
}