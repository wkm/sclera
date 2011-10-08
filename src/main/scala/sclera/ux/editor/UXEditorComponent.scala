
package sclera.ux.editor

import javax.swing.JTextPane
import sclera.ux.UXObjectComponent
import javax.swing.text._
import collection.immutable.{TreeMap, SortedMap}
import sclera.ux.wrappers.TextPaneComponent
import sclera.format.color.SolarizedColorPalette
import java.awt.{Dimension, Font, Graphics}

class UXEditorComponent
  extends TextPaneComponent {
  editorKit =  new ScalaEditorKit()
  contentType = "text/scala"
  font = new Font("Menlo", Font.BOLD, 12)
  foreground = SolarizedColorPalette("black")
}

class ScalaEditorKit(
  val viewFactory: ScalaViewFactory = new ScalaViewFactory()
) extends StyledEditorKit {
  override def getContentType = "text/xml"

  
}

class ScalaViewFactory extends ViewFactory {
  override def create(element: Element) =
    new ScalaView(element)
}

class ScalaView(val element: Element)
  extends PlainView(element) {

  getDocument.putProperty(PlainDocument.tabSizeAttribute, 4)

  override protected
  def drawUnselectedText(graphics: Graphics, x: Int, y: Int, p0: Int, p1: Int): Int = {
    val doc = getDocument
    val text = doc.getText(p0, p1 - p0)

    val segment = getLineBuffer

    val annotatedSource = new AnnotatedSource(text)

    val matches = UXEditorScalaKeywords.keywordRegexp.findAllIn(text)
    matches.matchData.foreach({ textmatch =>
      annotatedSource.addAnnotation(textmatch.start(0), textmatch.end(0))
    })

    return x
  }
}

class AnnotatedSource(text: String) {
  val annotationMap = new TreeMap[Tuple2[Int, Int], String]()

  def addAnnotation(start:Int, end:Int) {
    
  }
}