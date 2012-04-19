package me.choosenear.finagle.rest

import com.twitter.finagle.Service
import com.twitter.util.Future
import me.choosenear.finagle.rest.util.HttpFilter
import org.jboss.netty.buffer.ChannelBuffers.copiedBuffer
import org.jboss.netty.handler.codec.http.{HttpRequest, HttpResponse, DefaultHttpResponse}
import org.jboss.netty.handler.codec.http.HttpVersion.HTTP_1_1
import org.jboss.netty.handler.codec.http.HttpResponseStatus.OK
import org.jboss.netty.util.CharsetUtil.UTF_8

class RestFilter extends HttpFilter[RestRequest, RestResponse] {
  override def apply(request: HttpRequest, service: Service[RestRequest, RestResponse]): Future[HttpResponse] = {
    val restRequest = RestRequest.fromHttpRequest(request)
    service(restRequest).map {
      restResponse => {
        val callback: Option[String] = restRequest.params.optional[String]("callback")
        val body = restResponse.json
        val wrappedBody = callback.map(_ + "(" + body + ")").getOrElse(body)
        val response = new DefaultHttpResponse(HTTP_1_1, OK)
        response.setContent(copiedBuffer(wrappedBody, UTF_8))
        response
      }
    } handle {
      case ex @ RestException(msg, status) => {
        val response = new DefaultHttpResponse(HTTP_1_1, status)
        response.setContent(copiedBuffer(msg, UTF_8))
        ex.postProcess(response)
        response
      }
    }
  }
}
