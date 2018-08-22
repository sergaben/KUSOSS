package filters

import javax.inject.Inject

import akka.stream.Materializer
import play.api.Logger
import play.api.mvc.{Filter, Result}

import scala.concurrent.ExecutionContext
//import play.api.mvc.{Filter, Result}
import play.mvc.Http.RequestHeader

import scala.concurrent.Future
abstract class LoggingFilter @Inject() (implicit val mat: Materializer, ec: ExecutionContext) extends Filter {

  def apply(nextFilter: RequestHeader => Future[Result])
           (requestHeader: RequestHeader): Future[Result] = {

    val startTime = System.currentTimeMillis

    nextFilter(requestHeader).map { result =>

      val endTime = System.currentTimeMillis
      val requestTime = endTime - startTime

      Logger.info(s"${requestHeader.method} ${requestHeader.uri} took ${requestTime}ms and returned ${result.header.status}")

      result.withHeaders(
        "Request-Time" -> requestTime.toString,
        "Access-Control-Allow-Origin" -> "*"   // Added this header
      )
    }
  }
}

