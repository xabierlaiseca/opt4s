package me.laiseca.opt4s

class OptionsConfig {
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