package sclera.format.color

import java.awt.Color

class ColorPalette {
  var colors = Map[String, Color]()

  def addHexColor(name: String, hex: String) {
    addColor(name, Color.decode(hex.toUpperCase))
  }

  def addRgbColor(name: String, r:Int, g:Int, b:Int) {
    addColor(name, new Color(r, g, b))
  }

  def addColor(name: String, k: Color) {
    colors += name -> k
  }

  def apply(name: String): Color =
    return colors.get(name).getOrElse(Color.decode("0xd33682"))
}