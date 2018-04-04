package utils

import javax.inject.Inject
import play.api.Logger
import play.api.http.HttpVerbs
import play.api.i18n.MessagesApi
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

/**
  *
  * @project Backend
  * @author sergaben on 04/04/2018.
  *
  */

class LoginActionBuilder @Inject() (messageApi:MessagesApi,playBodyParsers: PlayBodyParsers)
                                   (implicit val executionContext: ExecutionContext)
  extends ActionBuilder[LoginRequest,AnyContent]
  with HttpVerbs{

  val parser: BodyParser[AnyContent] = playBodyParsers.anyContent

  type LoginRequestBlock[A] = LoginRequest[A] => Future[Result]

  private val logger = Logger(this.getClass)

  override def invokeBlock[A](request:Request[A],
                              block:LoginRequestBlock[A]):Future[Result] = {
    logger.trace(s"invokeBlock: ")

    val future = block(new LoginRequest(request,messageApi))

    future.map{ result =>
      request.method match{
        case GET | HEAD =>
          result.withHeaders("Cache-Control"->s"max-age:100")
        case other =>
          result
      }
    }

  }






}
