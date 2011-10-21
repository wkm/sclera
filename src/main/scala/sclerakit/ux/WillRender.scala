package sclerakit.ux

import swing.{Label, Component}

/**
 * sclerakit.ux.WillRender
 * wiktor - 2011
 */

trait WillRender {
  def render: Component
}

case class SimpleStyledText (
  val text: String
) extends WillRender {
  def render = new Label(text)
}