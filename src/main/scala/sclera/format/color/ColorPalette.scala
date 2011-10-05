package sclera.format.color

import java.awt.Color

class ColorPalette {
  var colors = Map[String, Color]()

  def color(name: String, r:Int, g:Int, b:Int) {
    colors += name -> new Color(r, g, b)
  }
}