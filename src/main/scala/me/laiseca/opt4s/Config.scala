package me.laiseca.opt4s

import scala.annotation.tailrec

class OptionsConfig(val defs: List[OptionDef[_]]) {
  validate()
  
  private def validate() {
    def validateOption(d: OptionDef[_], pos: Int) {
      require(d.long.isDefined || d.short.isDefined, s"Element number $pos must define either long or short name")
    }
    
    @tailrec
    def validate(defs: List[OptionDef[_]], pos: Int) {
      defs match {
        case d :: defsTail => {
          validateOption(d, pos)
          validate(defsTail, pos + 1)
        } 
        case List() =>
      } 
    }
    
    require(!defs.isEmpty, "Empty option list not allowed")
    validate(defs, 1)
  }
}

object OptionFunctions {
  def opt[T](long: String) = option[T](long, false)
  def opt[T](short: Char) = option[T](short, false)
  def opt[T](long: String, short: Char) = option[T](long,short, false)
  def flag[T](long: String) = option[T](long, true)
  def flag[T](short: Char) = option[T](short, true)
  def flag[T](long: String, short: Char) = option[T](long, short, true)
  
  private def option[T](long: String, flag: Boolean) =
    OptionDef[T](long = Option(long), flag = flag)
  
  private def option[T](short: Char, flag: Boolean) =
    OptionDef[T](short = Option(short), flag = flag)

  private def option[T](long: String, short: Char, flag: Boolean) =
    OptionDef[T](long = Option(long), short = Option(short), flag = flag)
}