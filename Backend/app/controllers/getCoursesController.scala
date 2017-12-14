package controllers

import javax.inject.Inject

import database.CourseRepositoryImpl
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import scala.concurrent.ExecutionContext
//TODO - ADD COMMIT AND PUSH TO GITHUB
//TODO - SENDING THE COURSES AS JSON
//Getting courses from database
class getCoursesController @Inject()(cc:ControllerComponents, courseRepositoryImpl: CourseRepositoryImpl)
                                    (implicit executionContext: ExecutionContext) extends AbstractController(cc){

  def getCourses: Action[AnyContent] = Action.async{
    courseRepositoryImpl.getAll().map { course =>
      Ok(Json.toJson(course))
    }
  }
}
