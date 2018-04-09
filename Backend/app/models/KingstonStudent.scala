package models

import org.joda.time.DateTime
import play.api.libs.json._

// simple case class for a student
case class KingstonStudent(id:Option[Int],nickname:String,email:String,password:String,subject:String,typeOfStudy:String,loginToken:String) {}

// companion object of KingstonStudent
object KingstonStudent{
  //This object is going to be used to format the class to json or from json to the class
  implicit object KingstonStudentFormat extends Format[KingstonStudent] {

    implicit val dateTimeWriter: Writes[DateTime] = JodaWrites.jodaDateWrites("dd/MM/yyyy HH:mm:ss")
    implicit val dateTimeJsReader: Reads[DateTime] = JodaReads.jodaDateReads("yyyyMMddHHmmss")

    def reads(json: JsValue): JsResult[KingstonStudent] = {
      // the Vals below hold all the parameters for the KingstonStudent class
      val id = (json \ "id_student").asOpt[Int]
      val nickname = (json \ "nickname").as[String]
      val email = (json \ "email").as[String]
      val password = (json \ "password").as[String]
      val subject = (json \ "subject").as[String]
      val typeOfStudy = (json \ "typeOfStudy").as[String]
      val loginToken = (json \ "login_token").as[String]
      // The line below returns a KingstonStudent object
      JsSuccess(KingstonStudent(id,nickname, email,password, subject, typeOfStudy,loginToken))
    }

    def writes(student: KingstonStudent): JsValue = {
      // the Seq below creates a json object
      val studentAsList = Seq(
        "id_student" -> Json.toJson(student.id),
        "nickname" -> JsString(student.nickname),
        "email" -> JsString(student.email),
        "password" -> JsString(student.password),
        "subject" -> JsString(student.subject),
        "typeOfStudy" -> JsString(student.typeOfStudy),
        "login_token"->Json.toJson(student.loginToken)
      )
      //The line below returns a json object
      JsObject(studentAsList)
    }
  }
}

// a id for a student
// a nickname for the student
// a password for the student
// an email for the student

