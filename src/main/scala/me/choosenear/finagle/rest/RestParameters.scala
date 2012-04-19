package me.choosenear.finagle.rest

import scala.collection.Map
import me.choosenear.finagle.rest.util.Parser

class MissingParameterException(parameterName: String)
extends RestException("Missing required parameter: " + parameterName)

class InvalidParameterException(parameterName: String, parameterValue: String)
extends RestException("Invalid parameter \""+parameterName+"\" with value: " + parameterValue)

class RestParameters(all: Map[String, Seq[String]]) {
  def many[T](name: String)(implicit parse: Parser[T]): Seq[T] =
    all.getOrElse(name, Nil).flatMap(parse.apply _)

  def optional[T](name: String)(implicit parse: Parser[T]): Option[T] =
    all.get(name).flatMap(xs => parse(xs.mkString(",")))

  def required[T](name: String)(implicit parse: Parser[T]): T = {
    val value = all.getOrElse(name, throw new MissingParameterException(name)).mkString(",")
    parse(value).getOrElse(throw new InvalidParameterException(name, value))
  }
}
