package models

import java.util.Date

import play.api.libs.json._

case class ExternalStudent(nickname:String,password:String,email:String,subject:String,typeOfStudy:String,expirationOfUser: Date)

object ExternalStudent {

  implicit object ExternalStudentFormat extends Format[ExternalStudent] {

    def reads(json: JsValue): JsResult[ExternalStudent] = {
      // the Vals below hold all the parameters for the KingstonStudent class
      val nickname = (json \ "nickname").as[String]
      val password = (json \ "password").as[String]
      val email = (json \ "email").as[String]
      val subject = (json \ "subject").as[String]
      val typeOfStudy = (json \ "typeOfStudy").as[String]
      val expirationOfUser = (json \ "expirationDate").as[Date]
      // The line below returns a KingstonStudent object
      JsSuccess(ExternalStudent(nickname, password, email, subject, typeOfStudy, expirationOfUser))
    }

    def writes(student: ExternalStudent): JsValue = {
      // the Seq below creates a json object
      val studentAsList = Seq("nickname" -> JsString(student.nickname),
        "password" -> JsString(student.password),
        "email" -> JsString(student.email),
        "subject" -> JsString(student.subject),
        "typeOfStudy" -> JsString(student.typeOfStudy),
        "expirationOfUser" -> JsString(student.expirationOfUser.toString))
      //The line below returns a json object
      JsObject(studentAsList)
    }
  }
}