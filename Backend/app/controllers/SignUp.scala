package controllers

import javax.inject.Inject

import database.KingstonStudentRepositoryImpl
import models.KingstonStudent
import org.mindrot.jbcrypt.BCrypt
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.ExecutionContext


class SignUp @Inject()(cc:ControllerComponents, kingstonStudentRepositoryImpl: KingstonStudentRepositoryImpl)
                      (implicit executionContext:ExecutionContext) extends AbstractController(cc){


//TODO - check if user is registered already checking the nickname and email
    def signup = Action{ req => // does this runs this query in blocking mode?
       val json = req.body.asJson.get
       val kStudent = json.as[KingstonStudent]
       val hashedPassword = BCrypt.hashpw(kStudent.password,BCrypt.gensalt())
       val kStudentWithHashedPassword = kStudent.copy(password = hashedPassword)
       println(kStudentWithHashedPassword)
       insertStudentIntoDatabase(kStudentWithHashedPassword)
       Ok
    }
  // then run query insert into database
    private def insertStudentIntoDatabase(kingstonStudent: KingstonStudent): Unit ={
      kingstonStudentRepositoryImpl.insert(kingstonStudent)
    }


}
