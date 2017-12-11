package controllers

import javax.inject.Inject

import database.CourseRepositoryImpl
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}
//TODO - ADD COMMIT AND PUSH TO GITHUB
//TODO - SENDING THE COURSES AS JSON
//Getting courses from database
class getCoursesController @Inject()(cc:ControllerComponents, courseRepositoryImpl: CourseRepositoryImpl)
                                    (implicit executionContext: ExecutionContext) extends AbstractController(cc){

  def getCourses = Action{
    // call to database which gets all the courses
    val allCourses = courseRepositoryImpl.getAll()
    // Handles the call when is completed
    allCourses onComplete{
      case Success(courses) => for (course <- courses) println(course)
      case Failure(f) => println("An error has occured: " + f.getMessage)
    }
    Ok
  }

}
