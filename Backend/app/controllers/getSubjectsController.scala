package controllers

import javax.inject.Inject

import database.SubjectRepositoryImpl
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}
//TODO - SEND SUBJECT NAMES AS JSON TO THE FRONT END
//TODO - ADD COMMIT AND PUSH TO GITHUB

class getSubjectsController @Inject()(cc:ControllerComponents, subjectRepositoryImpl: SubjectRepositoryImpl)
                                     (implicit executionContext: ExecutionContext) extends AbstractController(cc){

  def getSubjectsNames = Action{
    val allSubjectsNames = subjectRepositoryImpl.getAll()
    allSubjectsNames onComplete {
      case Success(subjects) => for (subject <- subjects) Json.toJson(subject)
      case Failure(f) => println("An error has occured " + f.getMessage)
    }
    Ok
  }

}
