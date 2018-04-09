package controllers

import database.KingstonStudentRepositoryImpl
import javax.inject.Inject
import models.KingstonStudent
import org.mindrot.jbcrypt.BCrypt
import play.api.libs.functional.syntax._
import play.api.libs.json._
import play.api.mvc.{AbstractController, Action, ControllerComponents, Result}

import scala.concurrent.{ExecutionContext, Future}

class Login @Inject()(cc:ControllerComponents,kingstonStudentRepositoryImpl: KingstonStudentRepositoryImpl)
                     (implicit executionContext:ExecutionContext) extends AbstractController(cc){

   case class LoginRequest(username:String,password:String)

  implicit val LoginRequestReads: Reads[LoginRequest] = (
    (JsPath \ "username").read[String] and
      (JsPath \ "matchPassword").read[String]
    )(LoginRequest.apply _)

  implicit val LoginRequestWrites = new Writes[LoginRequest] {
    def writes(loginRequest: LoginRequest): JsObject = Json.obj(
      "username" -> loginRequest.username,
      "matchPassword" -> loginRequest.password
    )
  }

  private def validateJson[A:Reads] = parse.json.validate(
    _.validate[A].asEither.left.map(e => BadRequest(JsError.toJson(e)))
  )
  // DONE - add authentication token in login request and send it as json

  def login: Action[LoginRequest] = Action.async(validateJson[LoginRequest]){ implicit req =>
    getFutureToCheckIfUserExists(kingstonStudentRepositoryImpl.getByNickname(req.body.username)){ checkStudent =>
      println(req.body.username)
      if(checkPasswordValidation(req.body.password, checkStudent.password)){
        val finalStudent = for{
          updatedStudent <- kingstonStudentRepositoryImpl.updateOrInsertToken(
            checkStudent.id,checkStudent.nickname, checkStudent.email, checkStudent.password, checkStudent.subject, checkStudent.typeOfStudy,checkStudent.loginToken
          )
        }yield updatedStudent
        getFutureToCheckIfUserExists(finalStudent){ updatedStudent:KingstonStudent =>
            Future.successful{Ok(Json.obj("status"->"OK","authenticated"->true,"nickname"->updatedStudent.nickname,"subject"->updatedStudent.subject,"token"->updatedStudent.loginToken))}
        }
//        finalStudent.flatMap{
//          case 0 => ))}
//          case n => Future.successful{Ok(Json.obj("status"->"OK","authenticated"->true,"nickname"->checkStudent.nickname,"subject"->checkStudent.subject,"token"->checkStudent.loginToken,"data"->n))}
//        }
      }else{
        Future.successful{Ok(Json.obj("status" -> "OK","authenticated" -> false,"nickname" -> "NONE"))}
      }
    }
  }

  private def getFutureToCheckIfUserExists[T](futureOptionBlock: Future[Option[T]])(foundBlock: (T => Future[Result])): Future[Result] = {
    futureOptionBlock.flatMap {
      case Some(found) =>
        foundBlock(found)
      case None =>
        Future.successful(Ok(Json.obj("status"->"OK","error"->"USER NOT FOUND")))
    }
  }
  private def getFutureToUpserT[T](futureOptionBlock: Future[Option[T]])(foundBlock: (T => Future[Result])): Future[Result] = {
    futureOptionBlock.flatMap {
      case Some(found) =>
        foundBlock(found)
      case None =>
        Future.successful(Ok(Json.obj("status"->"OK","updated"->true)))
    }
  }

  private def checkPasswordValidation(passwordFromFrontEnd:String,passwordFromDatabase:String): Boolean ={
    val foundStudent = BCrypt.checkpw(passwordFromFrontEnd,passwordFromDatabase)
    foundStudent
  }

}
