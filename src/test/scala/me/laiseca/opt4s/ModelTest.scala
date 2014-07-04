package me.laiseca.opt4s

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class OptionDefTest extends FlatSpec with Matchers {
  val long = "help"
  val short = 'h'
  
  "required()" should "create a new required OptionDef on optional option" in {
    OptionDef[String](long = Option(long), flag = false, optional = true) required() should be {
      OptionDef[String](long = Option(long), flag = false, optional = false)
    }
  }
  
  "options()" should "return only one short option" in {
    OptionDef[String](short = Option(short), flag = false).options should be {
      List("-" + short)
    }
  }
  
  it should "return only one long option" in {
    OptionDef[String](long = Option(long), flag = false).options should be {
      List("--" + long)
    }
  }
  
  it should "return both short & long options" in {
    OptionDef[String](long = Option(long), short = Option(short), flag = false).options should be {
      List("-" + short, "--" + long)
    }
  }
}