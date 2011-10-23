package sclera.evaluator

import sun.tools.javap.JavapPrinter
import tools.nsc.{Interpreter, Settings}
import sclera.ux.UXPad
import tools.nsc.interpreter.{Results, IMain}
import java.io._

import tools.nsc.interpreter.IR
import sclera.util.Loggable

/**
 * sclera.evaluator.InterpreterWrapper
 * wiktor - 2011
 */

case class InterpretedResult(status: IR.Result, value:AnyRef);

class InterpreterWrapper extends Loggable {
  val settings = new Settings()
  settings.embeddedDefaults[UXPad]

  val pipedWriter = new PipedWriter()
  val pipeReader = new PipedReader()
  val output = new PrintWriter(pipedWriter)
  val main = new ScleraIMain(settings/*, output*/)
  pipedWriter.connect(pipeReader)

  val strReader = new BufferedReader(pipeReader)
  
  def interpret(input: String) : InterpretedResult = {
    val result = main.interpret(input)
    val request = main.getPreviousRequest

    val value = result match {
      case IR.Success =>
        if(request.isEmpty)
          null
        else
          request.get.lineRep.call("$result")

      case IR.Error =>
        "<<error>>"

      case IR.Incomplete =>
        "<<incomplete>>"
    }
    
    logger.trace("{} => {}", input, value)

    return InterpretedResult(result, value)
  }


  def errorHandler(res: String) = {
    println("ERROR: "+res);
  }
}