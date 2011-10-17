package sclera.evaluator

import org.specs._
import tools.nsc.interpreter.IMain
import tools.nsc.Settings
import sclera.ux.UXPad

object IMainSpecs extends Specification {
  "IMain" should {
    "give term value" in {
      val settings = new Settings()
      settings.processArgumentString("-usejavacp")

      class MyIMain extends IMain(settings) {
        def lastRequest = prevRequestList.last
      }

      val n = new MyIMain()
      n.interpret("val a = 1")

      val r = n.lastRequest
      r.lineRep.call("$result").must_== (1)
    }
  }
}