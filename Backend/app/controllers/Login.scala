package controllers

import database.StudentRepositoryImpl
import javax.inject.Inject
import models.Student
import org.mindrot.jbcrypt.BCrypt
import play.api.libs.functional.syntax._
import play.api.libs.json._
import play.api.mvc.{AbstractController, Action, ControllerComponents, Result}

import scala.concurrent.{ExecutionContext, Future}

class Login @Inject()(cc:ControllerComponents, kingstonStudentRepository: StudentRepositoryImpl)
                     (implicit executionContext:ExecutionContext) extends AbstractController(cc){

   case class LoginRequest(username:String,password:String)

  implicit val LoginRequestReads: Reads[LoginRequest] = (
    (JsPath \ "username").read[String] and
      (JsPath \ "password").read[String]
    )(LoginRequest.apply _)

  implicit val LoginRequestWrites = new Writes[LoginRequest] {
    def writes(loginRequest: LoginRequest): JsObject = Json.obj(
      "username" -> loginRequest.username,
      "password" -> loginRequest.password
    )
  }

  private def validateJson[A:Reads] = parse.json.validate(
    _.validate[A].asEither.left.map(e => BadRequest(JsError.toJson(e)))
  )
  // DONE - add authentication token in login request and send it as json

  def login: Action[LoginRequest] = Action.async(validateJson[LoginRequest]){ implicit req =>
    getFutureToCheckIfUserExists(kingstonStudentRepository.getByNickname(req.body.username)){ checkStudent =>
      if(checkPasswordValidation(req.body.password, checkStudent.password)){
        val finalStudent = for{
          updatedStudent <- kingstonStudentRepository.updateOrInsertToken(
            checkStudent.id,checkStudent.nickname, checkStudent.email, checkStudent.password, checkStudent.subject, checkStudent.typeOfStudy,checkStudent.loginToken
          )
        }yield updatedStudent
        getFutureToUpsert(finalStudent,req.body.username){ updatedStudent:Student =>
            Future.successful{Ok(Json.obj("status"->"OK","authenticated"->true,"nickname"->updatedStudent.nickname,"subject"->updatedStudent.subject,"token"->updatedStudent.loginToken.getOrElse("token").toString,"id"->updatedStudent.id.getOrElse(1).toString))}
        }
      }else{
        Future.successful{Ok(Json.obj("status" -> "OK","authenticated" -> false))}
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

  private def getFutureToUpsert[T](futureOptionBlock: Future[Option[T]],nickname:String)(foundBlock: (T => Future[Result])): Future[Result] = {
    futureOptionBlock.flatMap {
      case Some(found) =>
        foundBlock(found)
      case None =>
        getFutureToCheckIfUserExists(kingstonStudentRepository.getByNickname(nickname)){ updatedUser =>{
          Future.successful(Ok(Json.obj("status"->"OK","authenticated"->true,"nickname"->updatedUser.nickname,"subject"->updatedUser.subject,"token"->updatedUser.loginToken.getOrElse("token").toString,"id"->updatedUser.id.getOrElse(1).toString)))
        }}

    }
  }

  private def checkPasswordValidation(passwordFromFrontEnd:String,passwordFromDatabase:String): Boolean ={
    val foundStudent = BCrypt.checkpw(passwordFromFrontEnd,passwordFromDatabase)
    foundStudent
  }

}
