package sclera.ux.wrappers

import swing.{TextComponent, Component}
import javax.swing.text.EditorKit
import javax.swing.event.{DocumentEvent, DocumentListener}
import javax.swing.{BorderFactory, JTextPane}
import java.awt.{Color, Dimension, Font}

/**
 * Lightweight container wrapper for JTextPane based on the source code
 * for the EditorPane container that wraps JEditorPane
 */
class TextPaneComponent extends TextComponent {
  override lazy val peer: JTextPane = new JTextPane() with SuperMixin

  def contentType: String = peer.getContentType
  def contentType_=(t: String) = peer.setContentType(t)

  def editorKit: EditorKit = peer.getEditorKit
  def editorKit_=(kit: EditorKit) = peer.setEditorKit(kit)
}