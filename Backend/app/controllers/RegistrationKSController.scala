package controllers

import javax.inject.Inject

import database.KingstonStudentRepositoryImpl
import models.KingstonStudent
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.ExecutionContext


class RegistrationKSController @Inject()(cc:ControllerComponents,kingstonStudentRepositoryImpl: KingstonStudentRepositoryImpl)
                                        (implicit executionContext:ExecutionContext) extends AbstractController(cc){


//  // mapping kingston student to form
//  // TODO - ADD VALIDATION TO THE FORM
//      val ksForm: Form[KingstonStudent] = Form(
//        mapping(
//          "nickname" -> text,
//          "password" -> text,
//          "email" -> text,
//          "subject" -> text,
//          "typeOfStudy" -> text
//        )((nickname,password,email,subject,typeOfStudy) => KingstonStudent(nickname,password,email,subject,typeOfStudy))
//         ((kingstonStudent:KingstonStudent) =>
//           Some(kingstonStudent.nickname,kingstonStudent.password,kingstonStudent.email,kingstonStudent.subject,kingstonStudent.typeOfStudy))
//      )
  // convert the json request body to scala object
    def registerKStudents = Action{ req => // does this runs this query in blocking mode?
       val json = req.body.asJson.get
       val kStudent = json.as[KingstonStudent]
//       val hashedPassword = BCrypt.hashpw(kStudent.password,BCrypt.gensalt())
//       val kStudentWithHashedPassword = kStudent.copy(password = hashedPassword)
//       println(kStudentWithHashedPassword)
       insertKStudentIntoDatabase(kStudent)
       Ok
    }
  // then convert object to table sql
    def insertKStudentIntoDatabase(kingstonStudent: KingstonStudent): Unit ={
      kingstonStudentRepositoryImpl.add(kingstonStudent)
    }
  // then run query insert into database

}
