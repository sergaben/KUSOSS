package controllers

import javax.inject.Inject

import models.KingstonStudent
import play.api.data.Forms._
import play.api.data._
import play.api.mvc.{AbstractController, ControllerComponents}


class RegistrationKSController @Inject()(cc:ControllerComponents)extends AbstractController(cc){


  // mapping kingston student to form
  // TODO - ADD VALIDATION TO THE FORM
      val ksForm: Form[KingstonStudent] = Form(
        mapping(
          "nickname" -> text,
          "password" -> text,
          "email" -> text,
          "subject" -> text,
          "typeOfStudy" -> text
        )((nickname,password,email,subject,typeOfStudy) => KingstonStudent(nickname,password,email,subject,typeOfStudy))
         ((kingstonStudent:KingstonStudent) =>
           Some(kingstonStudent.nickname,kingstonStudent.password,kingstonStudent.email,kingstonStudent.subject,kingstonStudent.typeOfStudy))
      )
  // convert the json request body to scala object
    def registerKStudents = Action{ req =>
       val json = req.body.asJson.get
       val kStudent = json.as[KingstonStudent]
       println(kStudent)
       Ok
    }
  // then convert object to table sql

  // then run query insert into database

}
