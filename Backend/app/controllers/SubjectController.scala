package controllers

import javax.inject.Inject

import database.SubjectRepositoryImpl
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import scala.concurrent.ExecutionContext


class SubjectController @Inject()(cc:ControllerComponents, subjectRepository: SubjectRepositoryImpl)
                                 (implicit executionContext: ExecutionContext) extends AbstractController(cc) {

  //send Json data to front End
  def getSubjectsNamesAsJson: Action[AnyContent] = Action.async {
    subjectRepository.getAll.map { result =>
      Ok(Json.toJson(result))
    }
  }
}
