package sclera.ux.wrappers

import swing._
import sclera.format.color.SolarizedColorPalette
/**
 * sclera.ux.wrappers.NestedTextComponent
 * wiktor - 2011
 */
class NestedTextComponent
  extends scala.swing.BoxPanel(Orientation.Vertical)
//  with SequentialContainer.Wrapper
{
//  override lazy val peer : JNestedTextComponent = {
//    new JNestedTextComponent with SuperMixin
//  }
  peer.setBackground(SolarizedColorPalette("white"))

  // we have to find and remove the existing component listeners to workaround
  // type coercion to javax.swing.JComponent
//  for(listener <- peer.getContainerListeners)
//    peer.removeContainerListener(listener)


  def selectedComponent: Option[java.awt.Component] = {
//    val start = peer.getSelectionStart
//    val end = peer.getSelectionEnd
//
//    val javaxComponent = peer.getComponentAt(start, end)
//    Option(javaxComponent)
    None
  }
}