package database

import javax.inject.Inject

import database.Schemas.CoursesSchema
import models.Course
import models.Intefaces.ICourseRepository
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class CourseRepositoryImpl @Inject()(protected val dbConfigProvider:DatabaseConfigProvider,coursesSchema:CoursesSchema)
                                    (implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] with ICourseRepository{

  import profile.api._

  private val courses = TableQuery[coursesSchema.CourseTable]

  override def getAll(): Future[Seq[Course]] = db.run(courses.result)

  override def add(course: Course) = Future()

  override def update(course: Course) = Future()

  override def delete(course: Course) = Future()

  override def getById(id: Int) = ???
}
