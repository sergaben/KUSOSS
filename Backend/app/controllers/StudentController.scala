package controllers


import javax.inject.Inject

import database.Students
import models.Ku_student
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent._

class StudentController @Inject()(students:Students, cc:ControllerComponents)
                                 (implicit executionContext: ExecutionContext) extends AbstractController(cc){

  implicit val ku_studentWrites = Json.writes[Ku_student]

  val listOfStudents = students.all()
  val resultOfStudents = for {
    result1 <- listOfStudents
  } yield result1




//  listOfStudents.onComplete({
//    case Success(seqStudent) =>for (student <- seqStudent) Ok(student)
//
//    case Failure(exception) =>println("An error has occured " + exception.getMessage)
//  })

  def index = Action.async{

    resultOfStudents.map{result =>
      Ok(result.mkString(","))
    }

  }
//  resultOfStudents onComplete  {
//    case result => Ok(result.foreach(student => student.map(_.nickname)))
//  }
//  Ok(resultOfStudents)
//
//  def returnStudentAsString(aStudent:Ku_student):String{
//
//  }
//  private val Students = TableQuery[Students]
//
//  def all() : Future[Seq[Student]] = db.run(Students.result)




}
