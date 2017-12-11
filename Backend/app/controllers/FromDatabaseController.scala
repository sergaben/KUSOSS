package controllers

import javax.inject.Inject

import database.CourseRepositoryImpl
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.util.{Failure, Success}

class FromDatabaseController @Inject()(cc:ControllerComponents,courseRepositoryImpl: CourseRepositoryImpl) extends AbstractController(cc){

  def fromDatabase = Action{
    val allCourses = courseRepositoryImpl.getAll()
    allCourses onComplete{
      case Success(courses) => for (course <- courses) println(course)
      case Failure(t) => println("An error has occured: " + t.getMessage)
    }
    Ok
  }

}
