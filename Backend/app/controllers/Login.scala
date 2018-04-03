package controllers

import database.KingstonStudentRepositoryImpl
import javax.inject.Inject
import org.mindrot.jbcrypt.BCrypt
import play.api.libs.functional.syntax._
import play.api.libs.json._
import play.api.mvc.{AbstractController, ControllerComponents}
import utils.FutureUtils._
import utils.{AuthErrors, CommonErrors, OtherErrors}

import scala.concurrent.ExecutionContext

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
  val errors = new CommonErrors with AuthErrors with OtherErrors

  def login = Action{ req =>
    // get the nickname and password from frontend
    // get the nickname and password from database
    // check that the password matched the one in the database
    // if it matches then send true otherwise send false

    val json = req.body.asJson.get
    val loginRequest = json.as[LoginRequest]
    var loginSuccess: String = "false"
    val result = for {
      student <- kingstonStudentRepositoryImpl.getByNickname(loginRequest.nickname) whenUndefined "The username could not be found"
    } yield{
//      println("The user was found")
      LoginRequest(student.nickname,student.password)
      if(student.!=(null)){
        loginSuccess = "true"
      }
    }

    result.recover {
      case fpe: FutureProcessingException => BadRequest(fpe.getMessage)
      case t: Throwable => InternalServerError
    }
    // val requestedLogin = getStudent(loginRequest.nickname)

////    println(requestedLogin)
//    val validRequest: Future[Option[KingstonStudent]] = requestedLogin.flatMap{
//      case Some(request) => checkPasswordValidation(loginRequest.password,request.password)
//      case None => Future.successful(None)
//    }
//    requestedLogin onComplete{
//      case Success(request) =>  {
////        println(request)
//
//      }
//      case Failure(e:Exception) =>{
//        println("The request did not go through")
//        println(e.getMessage)
//        errors.toResult(e)
////        println(e.getMessage)
//      }
//    }

    Ok(Json.obj("login Succesful" -> loginSuccess))
  }

  def checkPasswordValidation(loginRequest: LoginRequest,passwordToCheck:String): Unit ={
    val foundStudent = BCrypt.checkpw(loginRequest.password,passwordToCheck)
    val loginSuccessful = if(foundStudent) Ok(Json.toJson(foundStudent.toString)) else Ok(Json.toJson(foundStudent.toString))
    println(loginSuccessful)
  }

//  def getStudent(nickname:String):Future[Option[LoginRequest]]={
//    val result = for {
//      student<- kingstonStudentRepositoryImpl.getByNickname(nickname) // Future[Option[KingstonStudent]]
//    }yield LoginRequest(student.get.nickname,student.get.password)
//
//    result
//  }

}
