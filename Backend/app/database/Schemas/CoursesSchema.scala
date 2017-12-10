package database.Schemas

import javax.inject.Inject

import models.Course
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import scala.concurrent.ExecutionContext

class CoursesSchema @Inject ()(protected val dbConfigProvider:DatabaseConfigProvider)
                              (implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  class CourseTable (tag: Tag) extends Table[Course](tag,"Courses"){

    def course_name = column [String]("course_name")
    def subject_name = column [String]("subject")
    def type_of_study = column [String]("type_of_study")
    def * = (course_name, subject_name, type_of_study) <> (Course.tupled,Course.unapply _)

  }
}
