package me.choosenear.finagle.rest.util

trait Parser[T] {
  def apply(x: String): Option[T]
  def unapply(x: String): Option[T] = apply(x)
}

object Parser {
  implicit object StringParser extends Parser[String] {
    override def apply(x: String): Option[String] = Some(x)
  }

  implicit object DoubleParser extends Parser[Double] {
    override def apply(x: String): Option[Double] = {
      try {
        Some(x.toDouble)
      } catch {
        case ex: NumberFormatException => None
      }
    }
  }
}
