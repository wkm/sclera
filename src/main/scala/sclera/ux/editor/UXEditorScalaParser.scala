/**
 * This contains a trivial, regexp-based (and so insufficient) parser
 * for individual lines of Scala source.
 *
 * It's a stopgap solution for the source highlighter.
 */

package sclera.ux.editor

import collection.immutable.TreeMap
import util.matching.Regex

class WordRegexp {
  var regexp:String = null
  def words(strings: String*) {
    regexp = "\\b("+ strings.reduceLeft(_ + "|" + _) +")\\b"
  }
}

object UXEditorScalaKeywords extends WordRegexp {
  words(
    "case","do","else","for","if","match","while",
    "private", "protected",

    "return","throw","try","catch","finally","abstract","class","def",
    "extends","final","implicit","import","lazy","new","object",
    "override","package","private","protected","requires","sealed",
    "super","this","trait","type","val","var","with","yield"
  )
}

object UXEditorScalaTypes extends WordRegexp {
  words(
    "Unit","Int","Long","Byte","Short","Char","Float","Double","Boolean",
    "Any","AnyVal","AnyRef",
    "Nothing","Null","None",
    "Array","Seq","List",
    "String"
  )
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
  val Type = Value
}

object ScalaSourceHighlighter {

  case class Component(
      start: Int,
      end: Int,
      component: ScalaSourceComponent.Value
  )

  private val regexp = new Regex(
    "(?m)(/\\*.*\\*/)|(\"[^\n]*\")|(\"\"\".*\"\"\")|("+UXEditorScalaKeywords.regexp+")|("+UXEditorScalaTypes.regexp+")",
    "comment", "quote1", "quote2", "keywords", "", "types", ""
  )
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
      case _ if textmatch.group("types") != null => Type
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