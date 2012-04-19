package me.choosenear.finagle.rest

import org.jboss.netty.handler.codec.http.{HttpMethod, HttpRequest, QueryStringDecoder}
import scalaj.collection.Implicits._

case class RestRequest(method: HttpMethod, path: List[String], params: RestParameters, underlying: HttpRequest)

object RestRequest {
  def fromHttpRequest(request: HttpRequest): RestRequest = {
    val method = request.getMethod
    val decoder = new QueryStringDecoder(request.getUri)
    val path = decoder.getPath.split('/').toList.drop(1)
    val params = new RestParameters(decoder.getParameters.asScala.mapValues(_.asScala))
    RestRequest(method, path, params, request)
  }
}
