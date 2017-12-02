package database

import javax.inject.Inject

import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.ExecutionContext


class Students @Inject() (protected val dbConfigProvider:DatabaseConfigProvider)
                         (implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {
//  import profile.api._
//
//  private val Students = TableQuery[StudentsTable]
//
//  def all(): Future[Seq[KingstonStudent]] = db.run(Students.result)

//  private class StudentsTable(tag: Tag) extends Table[KingstonStudent](tag, "Ku_student") {
//    def id = column[Int]("id_student", O.PrimaryKey, O.AutoInc)
//    def nickname = column[String]("nickname")
//    def password = column[String]("password")
//    def email = column[String]("email")
//    def from_kingston = column[Boolean]("from_Kingston")
//    def subject = column[Subject]("subject_id")
//    def chatRoom = column[Seq[ChatRoom]]("chat_id")
//    //def expiration_time_of_user = column[Option[Timestamp]]("Expiration_time_of_user",O.Default(null))
//    def * = (id, nickname, password, email, from_kingston, subject, chatRoom) <> (KingstonStudent.tupled, KingstonStudent.unapply _)
//  }

}