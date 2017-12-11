package database.Schemas

import javax.inject.Inject

import models.Subject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.ExecutionContext

class SubjectSchema @Inject ()(protected val dbConfigProvider:DatabaseConfigProvider)
                              (implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  class SubjectTable (tag: Tag) extends Table[Subject](tag,"subject"){
    def subject_name = column [String]("subject_name")
    def * = subject_name <> (Subject.apply _, Subject.unapply)
  }
}
