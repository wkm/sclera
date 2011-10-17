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

    println(request.fullPath("res0"))

    println("DEFINED NAMES: "+request.definedNames)
    println("DEFINED SYMBS: "+request.definedSymbols)

    println("++++ VALUE OF TERM: "+main.valueOfTerm(request.fullPath("res0")))
    println("++++ EVAL: "+request.getEval)

    val value = main.valueOfTerm(main.mostRecentVar).getOrElse(sys.error("no result"))
    println("VALUE ====> "+value)

    println("VALUES:")


    for(name <- request.definedNames) {
      val value : this.main.global.Symbol = request.definedSymbols.get(name).get

      value match {
        case term: this.main.global.TermSymbol =>
          println("\tterm: "+name+" ==> "+value)
      }
    }
  }


  def errorHandler(res: String) = {
    println("ERROR: "+res);
  }
}