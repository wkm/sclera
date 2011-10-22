package sclerakit.ux

import swing.{Button, Label, GridBagPanel}

/**
 * sclerakit.ux.Table
 * wiktor - 2011
 */

case class Table (
  var cells: List[List[Any]] = null
)
  extends WillRender
{
  def render = {
    val panel = new GridBagPanel {
      val c = new Constraints
      c.weightx = 0
      c.weighty = 0

      var y = 0
      for(row <- cells) {
        var x = 0
        for(cell <- row) {
          x += 1

          c.gridx = x
          c.gridy = y
          c.anchor = GridBagPanel.Anchor.West
          c.ipadx = 5
          c.ipady = 1

          layout(RenderDispatch(cell).render) = c
        }
        y+= 1
      }
    }
    panel
  }
}