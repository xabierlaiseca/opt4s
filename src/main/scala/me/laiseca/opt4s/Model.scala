package me.laiseca.opt4s

private[opt4s] case class OptionDef[T](val long: Option[String] = Option.empty,
    val short: Option[Char] = Option.empty, val flag: Boolean, val optional: Boolean = true) {
  
  def required() = this.copy(optional = false)
  
  private[opt4s] lazy val options: List[String] =
    short.map(c => "-" + c).toList ::: long.map(s => "--" + s).toList
}