package me.laiseca.opt4s

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class OptionsConfigTest extends FlatSpec with Matchers {
  val long = "help"
  val short = 'h'
  val longOption = "--" + long
  val shortOption = "-" + short
  
  "OptionsConfig constructor" should "throw an exception when ampty options list is provided" in {
    an [IllegalArgumentException] should be thrownBy {
      new OptionsConfig(List())
    }
  }
  
  it should "not fail when a list of long named options is provided" in {
    val optionDef = OptionDef[String](long = Option(long), flag = false)
    val opts = List(optionDef)
    
    new OptionsConfig(opts).options should be {
      Map(longOption -> optionDef)
    }
  }
  
  it should "not fail when a list of short named options is provided" in {
    val optionDef = OptionDef[String](short = Option(short), flag = false)
    val opts = List(optionDef)
    
    new OptionsConfig(opts).options should be {
      Map(shortOption -> optionDef)
    }
  }
  
  it should "not fail when a list of long & short named options is provided" in {
    val optionDef = OptionDef[String](long = Option(long), short = Option(short), flag = false)
    val opts = List(optionDef)
    
    new OptionsConfig(opts).options should be {
      Map(shortOption -> optionDef, longOption -> optionDef)
    }
  }
  
  it should "fail when an unnamed option is provided" in {
    val opts = List(OptionDef[String](long = Option(long), flag = false),
        OptionDef[String](flag = false))
    
    val exception = intercept[IllegalArgumentException] {
      new OptionsConfig(opts)
    }
    
    exception.getMessage() should include ("Element number 2 ")
  }
}

class OptionFunctionsTest extends FlatSpec with Matchers {
  import OptionFunctions._
  
  val long = "help"
  val short = 'h'
  
  "opt function" should "create the expected OptionDef with long option name" in {
      opt[String](long) should be {
        OptionDef[String](long = Option(long), flag = false)
      }
  }
  
  it should "create the expected OptionDef with long & short option name" in {
	opt[String](long, short) should be {
	  OptionDef[String](long = Option(long), short = Option(short), flag = false)
	}
  }
  
  it should "create the expected OptionDef with short option name" in {
    opt[String](short) should be {
      OptionDef[String](short = Option(short), flag = false)
    }
  }
  
  "flag function" should "create the expected OptionDef with long option name" in {
      flag[String](long) should be {
        OptionDef[String](long = Option(long), flag = true)
      }
  }
  
  it should "create the expected OptionDef with long & short option name" in {
	flag[String](long, short) should be {
	  OptionDef[String](long = Option(long), short = Option(short), flag = true)
	}
  }
  
  it should "create the expected OptionDef with short option name" in {
    flag[String](short) should be {
      OptionDef[String](short = Option(short), flag = true)
    }
  }
}