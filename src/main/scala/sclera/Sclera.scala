
package sclera

import swing._
import swing.Color
import event.EditDone
import java.awt.{Dimension, Color}
import ux.{UXPad, UXPadEntry}
import javax.swing.{UIManager, BorderFactory}

object Sclera extends SimpleSwingApplication {
  System.setProperty("apple.laf.useScreenMenuBar", "true")
  System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Sclera")
  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
  
  def top = new UXPad()
}