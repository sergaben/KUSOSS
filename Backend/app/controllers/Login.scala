package controllers

import javax.inject.Inject

import database.KingstonStudentRepositoryImpl
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.ExecutionContext

class Login @Inject()(cc:ControllerComponents, kingstonStudentRepositoryImpl: KingstonStudentRepositoryImpl)
                     (implicit executionContext:ExecutionContext) extends AbstractController(cc){

  case class LoginRequest(nickname:String,password:String)

  implicit val LoginRequestReads: Reads[LoginRequest] = (
    (JsPath \ "nickname").read[String] and
      (JsPath \ "matchPassword").read[String]
    )(LoginRequest.apply _)

  implicit val LoginRequestWrites = new Writes[LoginRequest] {
    def writes(loginRequest: LoginRequest) = Json.obj(
      "nickname" -> loginRequest.nickname,
      "matchPassword" -> loginRequest.password
    )
  }

  def login = Action{ req =>
    // get the nickname and password from frontend
    // get the nickname and password from database
    // check that the password matched the one in the database
    // if it matches then send true otherwise send false
    val json = req.body.asJson.get
    val loginRequest = json.as[LoginRequest]
    getStudent(loginRequest.nickname)
    //println(loginRequest.nickname)
    Ok
  }

  def getStudent(nickname:String)={
    val result = kingstonStudentRepositoryImpl.getByNickname(nickname)
    println(result)
  }

}
