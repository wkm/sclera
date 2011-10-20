package sclera.ux.wrappers

import swing._
import sclera.format.color.SolarizedColorPalette
import javax.swing.{JPanel, Box}
import java.awt.BorderLayout

/**
 * sclera.ux.wrappers.NestedTextComponent
 * wiktor - 2011
 *
 * The basic model is a JPanel with a BorderLayout containing a Box;
 * generally nested within a ScrollPane. The key bit is that if we add
 * JTextPane widgets to this component they're maximally wide but
 * minimally tall
 */
class NestedTextComponent
  extends Panel
  with SequentialContainer.Wrapper
{
  lazy val box = new javax.swing.Box(Orientation.Vertical.id)
  override lazy val peer = {
    val panel = new javax.swing.JPanel with SuperMixin
    panel.setLayout(new BorderLayout)
    panel.add(box, BorderLayout.NORTH)
    panel
  }

  def add(component: Component) {
    box.add(component.peer)
  }
}