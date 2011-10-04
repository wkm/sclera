
package sclera

import swing._
import event.EditDone

object HelloWorld extends SimpleSwingApplication {
  def newField() = new TextField {
    text = "0"
    columns = 5
  }

  val celsius = newField()
  val fahrenheit = newField()

  def top = new MainFrame {
    title = "Convert Temperatures"
    contents = new FlowPanel(
      celsius, new Label(" Celsius =  "),
      fahrenheit, new Label(" Fahrenheit")
    )
  }

  listenTo(fahrenheit, celsius)
  reactions += {
    case EditDone(`fahrenheit`) =>
      val f = Integer.parseInt(fahrenheit.text)
      val c = (f - 32) * 5/9
      celsius.text = c.toString

    case EditDone(`celsius`) =>
      val c = Integer.parseInt(celsius.text)
      val f = c * 9/5 + 32
      fahrenheit.text = f.toString
  }
}