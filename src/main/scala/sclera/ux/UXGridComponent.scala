package sclera.ux

import swing.{GridBagPanel, Component}

class UXGridComponent(val cells: List[List[UXObjectComponent]])
  extends GridBagPanel
  with UXObjectComponent {

}