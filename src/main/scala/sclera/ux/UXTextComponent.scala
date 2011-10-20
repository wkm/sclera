package sclera.ux

import sclera.sclera.format.TextFormat
import swing.Label
import java.awt.Font
class UXTextComponent(
    override val text:String,
    var textFormat : Option[TextFormat] = Option.empty
)
  extends Label(text)
  with UXObjectComponent
{
  font = new Font("Helvetica", Font.PLAIN, 12);
}