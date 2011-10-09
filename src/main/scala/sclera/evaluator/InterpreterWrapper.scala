package sclera.evaluator

import tools.nsc.interpreter.IMain
import sun.tools.javap.JavapPrinter
import java.io.{PipedWriter, PrintWriter}
import tools.nsc.{Interpreter, Settings}
import sclera.ux.UXPad

/**
 * sclera.evaluator.InterpreterWrapper
 * wiktor - 2011
 */

class InterpreterWrapper {
  val settings = new Settings()
  settings.embeddedDefaults[UXPad]

  val pipedWriter = new PipedWriter()
  val output = new PrintWriter(pipedWriter)
  val main = new IMain(settings, output)

  println("#### Wrapper Created")
  
  def interpret(input: String) : Any = {
    println("---- INTERP: "+input)
    val result = main.interpret(input)
    println("INTERP["+input+"] => "+result)
  }

  def errorHandler(res: String) = {
    println("ERROR: "+res);
  }
}