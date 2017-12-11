package database

import javax.inject.Inject

import database.Schemas.CoursesSchema
import models.Course
import models.Intefaces.ICourseRepository
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

// Implementation of the course repository which will query the database
class CourseRepositoryImpl @Inject()(protected val dbConfigProvider:DatabaseConfigProvider,coursesSchema:CoursesSchema)
                                    (implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] with ICourseRepository{

  import profile.api._

  private val courses = TableQuery[coursesSchema.CourseTable]

  // It will get all the courses
  override def getAll(): Future[Seq[Course]] = db.run(courses.result)
  // It will add a new course to the course table in database
  override def add(course: Course) = Future()
  // It will update a new course in the database
  override def update(course: Course) = Future()
  // It will delete an existing course in the database
  override def delete(course: Course) = Future()
  // It will get a course depending on the id
  override def getById(id: Int) = ???
}
