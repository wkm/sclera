package sclera.util

import javax.swing.SwingUtilities

/**
 * sclera.util.SwingKit
 * wiktor - 2011
 */

object SwingKit {
  // Inspired by scalide.utils.BetterSwing.swingLater
  def executeLater[A](fn: => A) {
    SwingUtilities.invokeLater(
      new Runnable() {
        def run {
          fn
        }
      }
    )
  }

  def executeAndWait[A](fn: => A) {
    SwingUtilities.invokeAndWait(
      new Runnable() {
        def run {
          fn
        }
      }
    )
  }
}