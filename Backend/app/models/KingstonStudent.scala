package models

import play.api.libs.json._

// simple case class for a student
case class KingstonStudent(nickname:String,password:String,email:String,subject:String,typeOfStudy:String,fromKingston:Boolean) {}

// companion object of KingstonStudent
object KingstonStudent{
  //This object is going to be used to format the class to json or from json to the class
  implicit object KingstonStudentFormat extends Format[KingstonStudent] {

    def reads(json: JsValue): JsResult[KingstonStudent] = {
      // the Vals below hold all the parameters for the KingstonStudent class
      val nickname = (json \ "nickname").as[String]
      val password = (json \ "password").as[String]
      val email = (json \ "email").as[String]
      val subject = (json \ "subject").as[String]
      val typeOfStudy = (json \ "typeOfStudy").as[String]
      val fromKingston = (json \ "from_Kingston").as[Boolean]
      // The line below returns a KingstonStudent object
      JsSuccess(KingstonStudent(nickname, password, email, subject, typeOfStudy,fromKingston))
    }

    def writes(student: KingstonStudent): JsValue = {
      // the Seq below creates a json object
      val studentAsList = Seq("nickname" -> JsString(student.nickname),
        "password" -> JsString(student.password),
        "email" -> JsString(student.email),
        "subject" -> JsString(student.subject),
        "typeOfStudy" -> JsString(student.typeOfStudy),
        "from_Kingston" -> JsBoolean(student.fromKingston))
      //The line below returns a json object
      JsObject(studentAsList)
    }
  }
}

// a id for a student
// a nickname for the student
// a password for the student
// an email for the student

