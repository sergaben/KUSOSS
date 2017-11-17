package controllers


import javax.inject.Inject

import database.Students
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent._
import scala.util.{Failure, Success}

class StudentController @Inject()(students:Students, cc:ControllerComponents)
                                 (implicit executionContext: ExecutionContext) extends AbstractController(cc){

  val listOfStudents = students.all()

  listOfStudents.onComplete({
    case Success(seqStudent) =>for (student <- seqStudent) println(student)
    case Failure(exception) =>println("An error has occured " + exception.getMessage)
  })

  def index = Action{
    Ok(views.html.index("Hello world"))
  }
//  private val Students = TableQuery[Students]
//
//  def all() : Future[Seq[Student]] = db.run(Students.result)




}
