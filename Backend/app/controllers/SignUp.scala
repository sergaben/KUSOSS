package controllers

import database.KingstonStudentRepositoryImpl
import javax.inject.Inject
import models.KingstonStudent
import org.mindrot.jbcrypt.BCrypt
import play.api.data.Forms._
import play.api.data._
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.ExecutionContext


class SignUp @Inject()(cc:ControllerComponents, kingstonStudentRepositoryImpl: KingstonStudentRepositoryImpl)
                      (implicit executionContext:ExecutionContext) extends AbstractController(cc){



  val userForm: Form[KingstonStudent] = Form (
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
    def signup = Action{ req => // does this runs this query in blocking mode?
      userForm.bindFromRequest.fold(
        formWithErrors => {
          // binding failure, retrieve the form with errors
          BadRequest(Json.obj("status"->"OK","errors"->Json.toJson(formWithErrors)))
        },
        userData =>{
          //binding success, you get the actual value
          val hashedPassword = BCrypt.hashpw(userData.password,BCrypt.gensalt())
          val kStudentWithHashedPassword = userData.copy(password = hashedPassword)
          println(kStudentWithHashedPassword)
          insertStudentIntoDatabase(kStudentWithHashedPassword)
          Ok(Json.obj("status"->"OK","signup"->true))
        }
      )

    }
  // then run query insert into database
    private def insertStudentIntoDatabase(kingstonStudent: KingstonStudent): Unit ={
      kingstonStudentRepositoryImpl.insert(kingstonStudent)
    }


}
