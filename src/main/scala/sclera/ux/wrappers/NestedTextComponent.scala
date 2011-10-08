package sclera.ux.wrappers

import javax.swing.JTextPane
import java.io.StringReader
import swing._
import event._
import sclera.format.color.SolarizedColorPalette
import java.awt.event.{ContainerEvent, ContainerListener}

/**
 * sclera.ux.wrappers.NestedTextComponent
 * wiktor - 2011
 */
class NestedTextComponent extends scala.swing.Component with SequentialContainer.Wrapper {
  override lazy val peer : JNestedTextComponent = {
    new JNestedTextComponent with SuperMixin
  }
  peer.setBackground(SolarizedColorPalette("white"))

  // we have to find and remove the existing component listeners to workaround
  // type coercion to javax.swing.JComponent
  for(listener <- peer.getContainerListeners)
    peer.removeContainerListener(listener)
}