package controllers

import database.KingstonStudentRepositoryImpl
import javax.inject.Inject
import org.mindrot.jbcrypt.BCrypt
import play.api.libs.functional.syntax._
import play.api.libs.json._
import play.api.mvc.{AbstractController, ControllerComponents}
import play.filters.csrf.CSRF
import utils.FutureO

import scala.concurrent.{ExecutionContext, Future}

class Login @Inject()(cc:ControllerComponents, kingstonStudentRepositoryImpl: KingstonStudentRepositoryImpl)
                     (implicit executionContext:ExecutionContext) extends AbstractController(cc){

  case class LoginRequest(nickname:String,password:String)

  implicit val LoginRequestReads: Reads[LoginRequest] = (
    (JsPath \ "nickname").read[String] and
      (JsPath \ "matchPassword").read[String]
    )(LoginRequest.apply _)

  implicit val LoginRequestWrites = new Writes[LoginRequest] {
    def writes(loginRequest: LoginRequest): JsObject = Json.obj(
      "nickname" -> loginRequest.nickname,
      "matchPassword" -> loginRequest.password
    )
  }
  def validateJson[A:Reads] = parse.json.validate(
    _.validate[A].asEither.left.map(e => BadRequest(JsError.toJson(e)))
  )
  // TODO - add authentication token in login request and send it as json

  def login = Action.async(validateJson[LoginRequest]){ implicit req =>
    // get the nickname and password from frontend
    // get the nickname and password from database
    // check that the password matched the one in the database
    // if it matches then send true otherwise send false
    val token = CSRF.getToken.getOrElse("NONE")
    val userExist = for {
      user <- FutureO(kingstonStudentRepositoryImpl.getByNickname(req.body.nickname))
    } yield user

    userExist.future.flatMap(student =>{
      student match{
        case Some(newStudent) => Future.successful(if(checkPasswordValidation(req.body.password,newStudent.password))
          Ok(Json.obj("status"->"OK","authenticated"->true,"nickname"->newStudent.nickname,"authToken"->Json.toJsFieldJsValueWrapper(token))) else Ok(Json.obj("status"->"OK","Authenticated"->false,"nickname"->"NONE","authToken"->Json.toJsFieldJsValueWrapper(token))))
        case other => Future.successful(Ok(Json.obj("status"->"NOT_FOUND","error"->"user does not exist")))
      }
    })

  }

  def checkPasswordValidation(passwordFromFrontEnd:String,passwordFromDatabase:String): Boolean ={
    val foundStudent = BCrypt.checkpw(passwordFromFrontEnd,passwordFromDatabase)
    foundStudent
  }
}
