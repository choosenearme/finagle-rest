package me.choosenear.finagle.rest

import com.twitter.finagle.Service
import com.twitter.util.Future

case class RestRouter(routes: PartialFunction[List[String], Service[RestRequest, RestResponse]]) extends Service[RestRequest, RestResponse] {
  override def apply(request: RestRequest): Future[RestResponse] = {
    if (routes.isDefinedAt(request.path))
      Future(routes(request.path)(request)).flatMap(identity)
    else
      Future.exception(RestNotFoundException)
  }
}
