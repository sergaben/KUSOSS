package controllers

import javax.inject.Inject

import database.SubjectRepositoryImpl
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.ExecutionContext
//TODO - SEND SUBJECT NAMES AS JSON TO THE FRONT END
//TODO - ADD COMMIT AND PUSH TO GITHUB

class getSubjectsController @Inject()(cc:ControllerComponents, subjectRepositoryImpl: SubjectRepositoryImpl)
                                     (implicit executionContext: ExecutionContext) extends AbstractController(cc) {

  //send Json data to front End
  def getSubjectsNamesAsJson = Action.async { request =>
    subjectRepositoryImpl.getAll().map { result =>
      result.foreach(subject =>{
        Ok(Json.toJson(subject.name))
      })
      Ok(Json.toJson(result.))
    }
  }


}