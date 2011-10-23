/**
 * This contains a trivial, regexp-based (and so insufficient) parser
 * for individual lines of Scala source.
 *
 * It's a stopgap solution for the source highlighter.
 */

package sclera.ux.editor

import collection.immutable.TreeMap
import util.matching.Regex

object UXEditorScalaKeywords {
  val keywords = Array("class", "override", "new", "def", "extends", "import", "val", "var")
  val keywordRegexp = keywords.reduceLeft(_+"|"+_)
}

/**
 * the components of a scala source file
 */
object ScalaSourceComponent extends Enumeration {
  type ScalaSourceComponent = Value

  val Plain = Value
  val Keyword = Value
  val String = Value
  val Comment = Value
}

object ScalaSourceHighlighter {

  case class Component(
      start: Int,
      end: Int,
      component: ScalaSourceComponent.Value
  )

  private val regexp = new Regex("(?m)(/\\*.*\\*/)|(\"[^\n]*\")|(\"\"\".*\"\"\")|("+UXEditorScalaKeywords.keywordRegexp+")", "comment", "quote1", "quote2", "keywords")
  def highlight(source: String) =
    regexp.findAllIn(source).matchData.map(textmatch =>
      Component(textmatch.start, textmatch.end, categorizeMatch(textmatch))
    )

  private def categorizeMatch(textmatch: Regex.Match) = {
    import ScalaSourceComponent._
    textmatch match {
      case _ if textmatch.group("comment") != null => Comment
      case _ if textmatch.group("quote1") != null => String
      case _ if textmatch.group("quote2") != null => String
      case _ if textmatch.group("keywords") != null => Keyword
      case _ => Plain
    }
  }
}


class AnnotatedSource (
    val text: String
)
{
  val annotationMap = new TreeMap[(Int, Int), String]()

  def addAnnotation(start:Int, end:Int) {

  }
}