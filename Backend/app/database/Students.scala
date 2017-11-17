package database

import java.sql.Timestamp
import javax.inject.Inject

import models.Ku_student
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}


class Students @Inject() (protected val dbConfigProvider:DatabaseConfigProvider)
                         (implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  private val Students = TableQuery[StudentsTable]

  def all(): Future[Seq[Ku_student]] = db.run(Students.result)

  private class StudentsTable(tag: Tag) extends Table[Ku_student](tag, "Ku_student") {
    def id = column[Int]("id_student", O.PrimaryKey, O.AutoInc)
    def nickname = column[String]("nickname")
    def password = column[String]("password")
    def email = column[String]("email")
    def subject_id = column[Int]("subject_id")
    def type_of_study_id = column[Int]("type_of_study_id")
    def from_kingston = column[Boolean]("from_Kingston")
    def expiration_time_of_user = column[Option[Timestamp]]("Expiration_time_of_user",O.Default(null))
    def * = (id, nickname, password, email, subject_id, type_of_study_id, from_kingston, expiration_time_of_user) <> (Ku_student.tupled, Ku_student.unapply _)
  }

}