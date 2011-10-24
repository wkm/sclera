package sclerakit.ux

import sclera.util.Loggable
import reflect.Method

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
      case _ if obj == null => SimpleStyledText("(null)")
      case r: WillRender => r
      case s: String => SimpleStyledText(s)

      // simple values
      case t:Int     => t.toString
      case t:Long    => t.toString
      case t:Byte    => t.toString
      case t:Short   => t.toString
      case t:Char    => t.toString
      case t:Float   => t.toString
      case t:Double  => t.toString
      case t:Boolean => t.toString
      case a:Array[Any] => Table(a.map( item => List(item)))

      case m:java.lang.reflect.Method => m.getName+"()"

      case d:java.util.Date => "Date "+d.toString


      case any: AnyRef => any.getClass.getSimpleName+"@||"+any.toString+"||"

      case _ => SimpleStyledText("||"+obj.toString+"||")
    }
  }
}