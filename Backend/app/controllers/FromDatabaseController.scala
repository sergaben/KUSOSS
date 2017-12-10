package controllers

import javax.inject.Inject

import database.CourseRepositoryImpl
import play.api.mvc.{AbstractController, ControllerComponents}

class FromDatabaseController @Inject()(cc:ControllerComponents,courseRepositoryImpl: CourseRepositoryImpl) extends AbstractController(cc){

  def fromDatabase = Action{
    println(courseRepositoryImpl.getAll())
    Ok
  }

}
