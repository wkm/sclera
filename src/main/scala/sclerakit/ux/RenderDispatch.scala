package sclerakit.ux

import sclera.util.Loggable

/**
 * sclerakit.ux.RenderDispatch
 * wiktor - 2011
 */

object RenderDispatch
extends Loggable
{
  def apply(obj: Any) = dispatch(obj)

  /**
   * dispatch will continually apply rendering rules to the object
   * until finally an object with trait WillRender is returned
   */
  def dispatch(obj: Any): WillRender = {
    var interim = internalDispatch(obj)
    var limiter = 0
    while(!interim.isInstanceOf[WillRender] && limiter < 32) {
      limiter += 1
      interim = internalDispatch(interim)
    }

    interim match {
      case willRender: WillRender =>
        willRender

      case _ =>
        SimpleStyledText("<<could not render because of limiter>>")
    }
  }

  def internalDispatch(obj: Any): Any = {
    logger.trace("I-DISPATCH ON: {}", obj)
    obj match {
      // trivial cases
      case r: WillRender => r
      case s: String => SimpleStyledText(s)

      // simple values
      case t:Int => obj.toString
      case t:Long => obj.toString
      case t:Byte => obj.toString
      case t:Short => obj.toString
      case t:Char => obj.toString
      case t:Float => obj.toString
      case t:Double => obj.toString

      case any: AnyRef => any.getClass.getSimpleName+"@{{"+any.toString+"}}"

      case _ => SimpleStyledText("{{"+obj.toString+"}}")
    }
  }
}