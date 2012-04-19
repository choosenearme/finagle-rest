package me.choosenear.finagle.rest.util

import org.specs2.mutable._

class ParserSpec extends Specification {
  "When asked for a String the Parser" should {
    "yield the value parsed as a String" in {
      parseString("4.5").get must beEqualTo("4.5")
    }
  }

  "When asked for a Double the Parser" should {
    "yield the value parsed as a Double" in {
      parseDouble("4.5").get must_== 4.5
    }
  }

  def parseString(parameter: String)(implicit parse: Parser[String]) = {
    parse.apply(parameter)
  }

  def parseDouble(parameter: String)(implicit parse: Parser[Double]) = {
    parse.apply(parameter)
  }
}
