package controllers

import database.KingstonStudentRepositoryImpl
import javax.inject.Inject
import models.KingstonStudent
import org.mindrot.jbcrypt.BCrypt
import play.api.data.Forms._
import play.api.data._
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.ExecutionContext


class SignUp @Inject()(cc:ControllerComponents, kingstonStudentRepositoryImpl: KingstonStudentRepositoryImpl)
                      (implicit executionContext:ExecutionContext) extends AbstractController(cc){



  private val userForm: Form[KingstonStudent] = Form (
    mapping(
      "id" -> optional(number),
      "nickname"->nonEmptyText,
      "email"->nonEmptyText,
      "password"->nonEmptyText,
      "subject"->nonEmptyText,
      "typeOfStudy"->nonEmptyText,
      "loginToken"->optional(text)
    )(KingstonStudent.apply)(KingstonStudent.unapply)
  )



//TODO - check if user is registered already checking the nickname and email
    def signup = Action{ implicit req =>
      signUpFormAsJson()
    }

  /**
    *
    * @param kingstonStudent The student object to be inserted
    */
    private def insertStudentIntoDatabase(kingstonStudent: KingstonStudent): Unit ={
      kingstonStudentRepositoryImpl.insert(kingstonStudent)
    }

  /**
    *
    * @param request to be processed
    * @tparam A type of request
    * @return a result of Json
    */
    private def signUpFormAsJson[A]()(implicit request: Request[A]):Result = {
      def failure(badForm: Form[KingstonStudent]) = {
        implicit val messages = messagesApi.preferred(request)
        BadRequest(badForm.errorsAsJson)
      }

      def success(input: KingstonStudent) = {
        val hashedPassword = BCrypt.hashpw(input.password,BCrypt.gensalt())
        val kStudentWithHashedPassword = input.copy(password = hashedPassword)
        println(kStudentWithHashedPassword)
        insertStudentIntoDatabase(kStudentWithHashedPassword)
        Ok(Json.obj("status"->"OK","signup"->true))
      }
      userForm.bindFromRequest().fold(failure,success)
    }


}
