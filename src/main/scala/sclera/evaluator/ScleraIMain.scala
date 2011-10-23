package sclera.evaluator

import tools.nsc.interpreter.IMain
import tools.nsc.Settings

/**
 * sclera.evaluator.ScleraIMain
 * wiktor - 2011
 */

class ScleraIMain (
  override val settings: Settings
//  override val out: java.io.PrintWriter
) extends IMain(settings) {
  /**
   * 
   */
  def getPreviousRequest =
    if(prevRequestList.isEmpty)
      None
    else
      Some(prevRequestList.last)
}