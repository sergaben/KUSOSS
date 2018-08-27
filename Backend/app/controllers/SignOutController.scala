package controllers

import database.StudentRepositoryImpl
import javax.inject.Inject
import play.api.libs.json._
import play.api.mvc.{AbstractController, Action, ControllerComponents}

import scala.concurrent.{ExecutionContext, Future}

/**
  *
  * @project Backend
  * @author sergaben on 08/04/2018.
  *
  */
class SignOutController @Inject()(cc:ControllerComponents, kingstonStudentRepository: StudentRepositoryImpl)
                                 (implicit executionContext:ExecutionContext) extends AbstractController(cc){
  //DONE - Add logout functionality, checking the current token and if is in the database update it to null and do the same with the local storage in frontend
  case class SignoutRequest(username:String)

  implicit val SignouRequestReads: Reads[SignoutRequest] = (JsPath \ "username").read[String].map(SignoutRequest.apply _)

  implicit val SignoutRequestWrites: Writes[SignoutRequest] {
    def writes(logOutRequest: SignoutRequest): JsObject
  } = new Writes[SignoutRequest] {
    def writes(logOutRequest: SignoutRequest): JsObject = Json.obj(
      "username" -> logOutRequest.username
    )
  }
  private def validateJson[A:Reads] = parse.json.validate(
    _.validate[A].asEither.left.map(e => BadRequest(JsError.toJson(e)))
  )

  def signout: Action[SignoutRequest] = Action.async(validateJson[SignoutRequest]){ implicit req =>
    kingstonStudentRepository.updateTokenToNull(req.body.username).flatMap{ updated =>
      if(updated>0){
        Future.successful(Ok(Json.obj("status"->"OK","logout"->true)))
      }else{
        Future.successful(Ok(Json.obj("status"->"OK","logout"->false)))
      }
    }
  }
}
