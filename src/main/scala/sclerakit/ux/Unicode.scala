package sclerakit.ux

import swing.Label

object Unicode {
  def character(codepoint: Int) = new String(Array[Int](codepoint), 0, 1)

  val ArrowLeft  = character(8592)
  val ArrowRight = character(8594)
  val ArrowUp    = character(8593)
  val ArrowDown  = character(8595)
}

