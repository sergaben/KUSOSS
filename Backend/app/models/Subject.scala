package models

import play.api.libs.json._

case class Subject(name:String)

object Subject {

  implicit object SubjectFormat extends Format[Subject] {

    def reads(json: JsValue): JsResult[Subject] = {
      // the Vals below hold all the parameters for the Subject class
      val name = (json \ "name").as[String]
      // The line below returns a Subject object
      JsSuccess(Subject(name))
    }

    def writes(subject:Subject): JsValue = {
      // the Seq below creates a json object
      val subjectAsList = Seq("name" -> JsString(subject.name))
      //The line below returns a json object
      JsObject(subjectAsList)
    }
  }
}
