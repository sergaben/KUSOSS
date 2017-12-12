package models

import play.api.libs.json._

case class Course (name:String, subject:String,type_of_study:String)

object Course {

  implicit object CourseFormat extends Format[Course] {

    def reads(json: JsValue): JsResult[Course] = {
      // the Vals below hold all the parameters for the Course case class
      val name = (json \ "name").as[String]
      val subject = (json \ "subject").as[String]
      val type_of_study = (json \ "type_of_study").as[String]
      // The line below returns a Course object
      JsSuccess(Course(name,subject,type_of_study))
    }

    def writes(course:Course): JsValue = {
      // the Seq below creates a json object
      val subjectAsList = Seq("name" -> JsString(course.name),
                              "subject"->JsString(course.subject),
                              "type_of_study"->JsString(course.type_of_study))
      //The line below returns a json object
      JsObject(subjectAsList)
    }
  }
}