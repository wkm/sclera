package sclera.evaluator

import org.specs._
import tools.nsc.interpreter.IMain
import tools.nsc.Settings
import sclera.ux.UXPad

object IMainSpecs extends Specification {
  "IMain" should {
    "give term value" in {
      val settings = new Settings()
      settings.embeddedDefaults[Specification]

      class MyIMain extends IMain(settings) {
        def lastRequest = prevRequestList.last
      }

      val n = new MyIMain()
      n.interpret("val a = 1; val b = 2")

      // this isn't ideal: we should be able to get the values of
      // a and b separately
      n.requestForIdent("a").get.lineRep.call("$result").must_== (2)
      n.requestForIdent("b").get.lineRep.call("$result").must_== (2)
    }
  }
}