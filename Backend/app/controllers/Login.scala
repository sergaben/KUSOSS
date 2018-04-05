package controllers

import database.KingstonStudentRepositoryImpl
import javax.inject.Inject
import org.mindrot.jbcrypt.BCrypt
import play.api.libs.functional.syntax._
import play.api.libs.json._
import play.api.mvc.{AbstractController, ControllerComponents}
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
//  val errors = new CommonErrors with AuthErrors with OtherErrors

  def login = Action.async(validateJson[LoginRequest]){ implicit req =>
    // get the nickname and password from frontend
    // get the nickname and password from database
    // check that the password matched the one in the database
    // if it matches then send true otherwise send false
//    println(req)
    val userExist = for {
                        user <- FutureO(kingstonStudentRepositoryImpl.getByNickname(req.body.nickname))
                      } yield user
    userExist.future.flatMap(student =>{
      student match{
        case Some(newStudent) => Future.successful(if(checkPasswordValidation(req.body.password,newStudent.password)) Ok(Json.obj("status"->"OK","authenticated"->"true","nickname"->newStudent.nickname)) else Ok(Json.obj("status"->"OK","Authenticated"->"false","nickname"->"NONE")))
        case other => Future.successful(Ok(Json.obj("status"->"NOT_FOUND","error"->"user does not exist")))
      }
    })
//    userExist.future onComplete{
//      case Success(student) =>{
//        student match{
//          case Some(newStudent) => if(checkPasswordValidation(req.body.password,newStudent.password)) Ok(Json.obj("status"->"OK","Authenticated"->"true","nickname"->newStudent.nickname)) else Ok(Json.obj("status"->"OK","Authenticated"->"false","nickname"->"NONE"))
//          case other => Ok(Json.obj("status"->"NOT_FOUND","error"->"user does not exist"))
//        }
//      }
//      case Failure(error) =>{
//       Ok(Json.obj("status"->"INTERNAL_ERROR","error"->error.getMessage))
//      }
//    }
//      println("The user was found")
//      println(loginSuccess)
//      loginSuccess = "true"
//      LoginRequest(student.nickname,student.password)
//
//
//    }
//    val finalResult : Result = result onComplete {
//      case Success(request) =>{
//       Ok(Json.obj("status"->"OK","login Succesful" -> request.nickname))
//      }
//      case Failure(e:Exception) =>{
//        Ok(Json.obj("status"->"KO","login Succesful" -> "User Not found"))
//      }
//    }
//    finalResult
//    result.recover {
//      case fpe: FutureProcessingException => BadRequest(fpe.getMessage)
//      case t: Throwable => InternalServerError
//    }
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
//    println(Json.obj("status"->"KO","login Succesful" -> loginSuccess))
//    Json.obj("status"->"KO","login Succesful" -> loginSuccess)
//    println(loginSuccess)
  }

  def checkPasswordValidation(passwordFromFrontEnd:String,passwordFromDatabase:String): Boolean ={
    val foundStudent = BCrypt.checkpw(passwordFromFrontEnd,passwordFromDatabase)
    foundStudent
//    val loginSuccessful = if(foundStudent) Ok(Json.toJson(foundStudent.toString)) else Ok(Json.toJson(foundStudent.toString))
//    println(loginSuccessful)
  }

//  def getStudent(nickname:String):Future[Option[LoginRequest]]={
//    val result = for {
//      student<- kingstonStudentRepositoryImpl.getByNickname(nickname) // Future[Option[KingstonStudent]]
//    }yield LoginRequest(student.get.nickname,student.get.password)
//
//    result
//  }

}
