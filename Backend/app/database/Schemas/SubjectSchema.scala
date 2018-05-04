package database.Schemas

import javax.inject.Inject
import models.Subject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

// a class that represents the schema of the Subject Table in the database
class SubjectSchema @Inject ()(protected val dbConfigProvider:DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  class SubjectTable (tag: Tag) extends Table[Subject](tag,"subject"){
    def subject_name = column [String]("subject_name")
    def * = subject_name <> (Subject.apply _, Subject.unapply)
  }

  val subjects = TableQuery[SubjectTable] // represents a database table
}
