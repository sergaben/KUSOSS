package controllers

import database.KingstonStudentRepository
import javax.inject.Inject
import play.api.libs.json._
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import scala.concurrent.{ExecutionContext, Future}

/**
  *
  * @project Backend
  * @author sergaben on 08/04/2018.
  *
  */
class LogOut @Inject()(cc:ControllerComponents, kingstonStudentRepository: KingstonStudentRepository)
                     (implicit executionContext:ExecutionContext) extends AbstractController(cc){
  //DONE - Add logout functionality, checking the current token and if is in the database update it to null and do the same with the local storage in frontend
  case class LogoutRequest(username:String)

  implicit val LogoutRequestReads: Reads[LogoutRequest] = (JsPath \ "username").read[String].map(LogoutRequest.apply _)

  implicit val LogoutRequestWrites: Writes[LogoutRequest] {
    def writes(logOutRequest: LogoutRequest): JsObject
  } = new Writes[LogoutRequest] {
    def writes(logOutRequest: LogoutRequest): JsObject = Json.obj(
      "username" -> logOutRequest.username
    )
  }
  private def validateJson[A:Reads] = parse.json.validate(
    _.validate[A].asEither.left.map(e => BadRequest(JsError.toJson(e)))
  )

  def logout: Action[LogoutRequest] = Action.async(validateJson[LogoutRequest]){ implicit req =>
    kingstonStudentRepository.updateTokenToNull(req.body.username).flatMap{ updated =>
      if(updated>0){
        Future.successful(Ok(Json.obj("status"->"OK","logout"->true)))
      }else{
        Future.successful(Ok(Json.obj("status"->"OK","logout"->false)))
      }
    }
  }
}
