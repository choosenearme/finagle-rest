package me.choosenear.finagle.rest

import org.jboss.netty.handler.codec.http.{HttpResponse, HttpResponseStatus}
import org.jboss.netty.handler.codec.http.HttpResponseStatus.{BAD_REQUEST, FOUND, NOT_FOUND}

case class RestException(message: String, status: HttpResponseStatus = BAD_REQUEST) extends RuntimeException(message) {
  def postProcess(response: HttpResponse): Unit = ()
}

object RestNotFoundException extends RestException("", NOT_FOUND)
