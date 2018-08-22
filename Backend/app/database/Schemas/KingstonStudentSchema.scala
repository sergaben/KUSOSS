package database.Schemas

import javax.inject.Inject
import models.Student
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

class KingstonStudentSchema @Inject()(protected val dbConfigProvider:DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  class kingstonStudentTable (tag:Tag) extends Table[Student](tag,"Ku_student"){
    def id = column[Option[Int]]("id_student",O.PrimaryKey,O.AutoInc)
    def nickname = column [String]("nickname")
    def email = column[String]("email")
    def password = column[String]("password")
    def subject= column[String]("subject")
    def typeOfStudy = column[String]("typeOfStudy")
    def loginToken = column[Option[String]]("login_token")
    def * = (id,nickname,email,password,subject,typeOfStudy,loginToken) <> ((Student.apply _).tupled, Student.unapply _)
  }


  val Kstudents = TableQuery[kingstonStudentTable]
}
