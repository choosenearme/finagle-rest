package me.choosenear.finagle.rest

import com.twitter.finagle.Service
import com.twitter.util.Future
import org.jboss.netty.handler.codec.http.{HttpMethod, DefaultHttpResponse}
import org.jboss.netty.handler.codec.http.HttpResponseStatus.NOT_FOUND

class RestResource extends Service[RestRequest, RestResponse] {
  override def apply(request: RestRequest): Future[RestResponse] = request match {
    case RestRequest(HttpMethod.GET, _, _, _) => get(request)
    case RestRequest(HttpMethod.PUT, _, _, _) => put(request)
    case RestRequest(HttpMethod.POST, _, _, _) => post(request)
    case RestRequest(HttpMethod.DELETE, _, _, _) => delete(request)
  }

  def get(request: RestRequest) = notFound(request)
  def put(request: RestRequest) = notFound(request)
  def post(request: RestRequest) = notFound(request)
  def delete(request: RestRequest) = notFound(request)

  def notFound(request: RestRequest): Future[RestResponse] =
    Future.exception(RestNotFoundException)
}
