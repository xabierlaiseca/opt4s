package me.laiseca.opt4s

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class ConfigTest extends FlatSpec with Matchers {
  val long = "help"
  val short = 'h'
  
  "opt with long option name" should "create the expected OptionDef" in {
      new OptionsConfig().opt[String](long) should be {
        OptionDef[String](long = Option(long), flag = false)
      }
  }
  
  "opt with long & short option name" should "create the expected OptionDef" in {
	new OptionsConfig().opt[String](long, short) should be {
	  OptionDef[String](long = Option(long), short = Option(short), flag = false)
	}
  }
  
  "opt with short option name" should "create the expected OptionDef" in {
    new OptionsConfig().opt[String](short) should be {
      OptionDef[String](short = Option(short), flag = false)
    }
  }
  
  "required opt" should "create the expected OptionDef" in {
    new OptionsConfig().opt[String](long) required() should be {
      OptionDef[String](long = Option(long), flag = false, optional = false)
    }
  }
  
  "flag with long option name" should "create the expected OptionDef" in {
      new OptionsConfig().flag[String](long) should be {
        OptionDef[String](long = Option(long), flag = true)
      }
  }
  
  "flag with long & short option name" should "create the expected OptionDef" in {
	new OptionsConfig().flag[String](long, short) should be {
	  OptionDef[String](long = Option(long), short = Option(short), flag = true)
	}
  }
  
  "flag with short option name" should "create the expected OptionDef" in {
    new OptionsConfig().flag[String](short) should be {
      OptionDef[String](short = Option(short), flag = true)
    }
  }
  
  "required flag" should "create the expected OptionDef" in {
    new OptionsConfig().flag[String](long) required() should be {
      OptionDef[String](long = Option(long), flag = true, optional = false)
    }
  }
}