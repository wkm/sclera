package sclera.evaluator

import sun.tools.javap.JavapPrinter
import tools.nsc.{Interpreter, Settings}
import sclera.ux.UXPad
import tools.nsc.interpreter.{Results, IMain}
import java.io._

/**
 * sclera.evaluator.InterpreterWrapper
 * wiktor - 2011
 */

class InterpreterWrapper {
  val settings = new Settings()
  settings.embeddedDefaults[UXPad]

  val pipedWriter = new PipedWriter()
  val pipeReader = new PipedReader()
  val output = new PrintWriter(pipedWriter)
  val main = new ScleraIMain(settings, output)
  pipedWriter.connect(pipeReader)

  val strReader = new BufferedReader(pipeReader)

  println("#### Wrapper Created")
  
  def interpret(input: String) : Any = {
    println("---- INTERP: "+input)
    val result = main.interpret(input)
    val request = main.getPreviousRequest

    println("| result:  "+result);
    println("| eval:    "+request.getEval)
    println("| types:   "+request.typeNames)
    println("| terms:   "+request.termNames)
  }

  def errorHandler(res: String) = {
    println("ERROR: "+res);
  }
}