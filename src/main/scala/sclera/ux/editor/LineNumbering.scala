package sclera.ux.editor

import javax.swing.JPanel
import javax.swing.event.{CaretEvent, DocumentEvent, DocumentListener, CaretListener}
import sclera.util.SwingKit
import java.beans.{PropertyChangeEvent, PropertyChangeListener}
import sclerakit.ux.Border
import sclera.ux.formatting.LineNumberFormatting
import javax.swing.text.{Utilities, JTextComponent}
import sclera.format.color.SolarizedColorPalette
import java.awt.{FontMetrics, Point, Graphics, Font}

/**
 * sclera.ux.editor.LineNumbering
 * wiktor - 2011
 */

class LineNumbering
(
    component: JTextComponent,
    minDigits: Int = 3
)
  extends JPanel
  with CaretListener
  with DocumentListener
  with PropertyChangeListener
{
  private val MAX_HEIGHT = Integer.MAX_VALUE - 1000000

  private val outerBorder = Border("", LineNumberFormatting).render.peer

  var updateFont = false
  var borderGap = 3
  var digitAlignment = 1.0f
  setFont(new Font("Helvetica", Font.PLAIN, 9))

  component.getDocument.addDocumentListener(this)
  component.addCaretListener(this)
  component.addPropertyChangeListener("font", this)


  private def setPreferredWidth {
    val root = component.getDocument.getDefaultRootElement
    val lines = root.getElementCount
    val digits = lines.toString.length

    val fontMetrics = getFontMetrics(getFont())
    val width = fontMetrics.charWidth('0') * digits
    val insets = getInsets
    val preferredWidth = insets.left + insets.right + width

    val preferredSize = getPreferredSize
    preferredSize.setSize(preferredWidth, MAX_HEIGHT)
    setPreferredSize(preferredSize)
    setSize(preferredSize)
  }

  override
  def paintComponent(g: Graphics) {
    super.paintComponent(g)

    val fontMetrics = component.getFontMetrics( component.getFont() )
    val insets = getInsets()
		val availableWidth = getSize().width - insets.left - insets.right;
    val clip = g.getClipBounds
    var rowStartOffset = component.viewToModel( new Point(0, clip.y) );
		val endOffset = component.viewToModel( new Point(0, clip.y + clip.height) );

    while (rowStartOffset <= endOffset)
		{
      g.setColor(SolarizedColorPalette("base1"));

      val lineNumber = textLineNumber(rowStartOffset);
      val stringWidth = fontMetrics.stringWidth( lineNumber );
      val x = offsetX(availableWidth, stringWidth)
      val y = offsetY(rowStartOffset, fontMetrics)
      g.drawString(lineNumber, x, y);

      //  Move to the next row
      rowStartOffset = Utilities.getRowEnd(component, rowStartOffset) + 1;
		}
  }

  private def textLineNumber(rowStartOffset: Int) = {
    val root = component.getDocument.getDefaultRootElement
    val index = root.getElementIndex(rowStartOffset)
    val line = root.getElement(index)

    if(line.getStartOffset == rowStartOffset)
      (index+1).toString
    else
      "-"
  }

  private def offsetX(availableWidth: Int, stringWidth: Int) =
    availableWidth - stringWidth

  private def offsetY(rowStartOffset: Int, fontMetrics: FontMetrics) = {
    val r = component.modelToView( rowStartOffset )
		val y = r.y + r.height
		var descent = fontMetrics.getDescent()
		y - descent - 1
  }

  override
  def caretUpdate(e: CaretEvent) {
    println("CARET UPDATE")
  }

  override
  def changedUpdate(e: DocumentEvent) {
    documentChanged()
  }

  override
  def insertUpdate(e: DocumentEvent) {
    documentChanged()
  }

  override
  def removeUpdate(e: DocumentEvent) {
    documentChanged()
  }

  override
  def propertyChange(e: PropertyChangeEvent) {
    println("PROPERTY CHANGED")
  }

  var lastHeight: Int = 0
  private
  def documentChanged() {
    component.repaint()

    SwingKit.executeLater(() => {
      val preferredHeight = component.getPreferredSize.height

      if(lastHeight != preferredHeight) {
        setPreferredWidth
        repaint()
        lastHeight = preferredHeight
      }
    })
  }
  

}