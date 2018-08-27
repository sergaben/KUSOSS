package controllers

import database.StudentRepositoryImpl
import javax.inject.Inject
import models.Student
import org.mindrot.jbcrypt.BCrypt
import play.api.data.Forms._
import play.api.data._
import play.api.i18n.Messages
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.ExecutionContext



class SignUpController @Inject()(cc:ControllerComponents, kingstonStudentRepository: StudentRepositoryImpl)
                                (implicit executionContext:ExecutionContext) extends AbstractController(cc){
  private val userForm: Form[Student] = Form (
    mapping(
      "id" -> optional(number),
      "nickname"->nonEmptyText,
      "email"->nonEmptyText,
      "password"->nonEmptyText,
      "subject"->nonEmptyText,
      "typeOfStudy"->nonEmptyText,
      "loginToken"->optional(text)
    )(Student.apply)(Student.unapply)
  )
    def signup = Action{ implicit req =>
      signUpFormAsJson()
    }
    private def insertStudentIntoDatabase(kingstonStudent: Student): Unit ={
      kingstonStudentRepository.insert(kingstonStudent)
    }
  /**
    *
    * @param request to be processed
    * @tparam A type of request
    * @return a result of Json
    */
    private def signUpFormAsJson[A]()(implicit request: Request[A]):Result = {
      def failure(badForm: Form[Student]) = {
        implicit val messages: Messages = messagesApi.preferred(request)
        BadRequest(badForm.errorsAsJson)
      }

      def success(input: Student) = {
        val hashedPassword = BCrypt.hashpw(input.password,BCrypt.gensalt())
        val kStudentWithHashedPassword = input.copy(password = hashedPassword)
        println(kStudentWithHashedPassword)
        insertStudentIntoDatabase(kStudentWithHashedPassword)
        Ok(Json.obj("status"->"OK","successful"->true))
      }
      userForm.bindFromRequest().fold(failure,success)
    }
}
