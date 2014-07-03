package me.laiseca.opt4s

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class OptionDefTest extends FlatSpec with Matchers {
  val long = "help"
  
  "required() on optional option" should "create a new required OptionDef" in {
    OptionDef[String](long = Option(long), flag = false, optional = true) required() should be {
      OptionDef[String](long = Option(long), flag = false, optional = false)
    }
  }
}