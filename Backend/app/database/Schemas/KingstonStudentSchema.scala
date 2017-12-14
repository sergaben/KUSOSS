package database.Schemas

import javax.inject.Inject

import models.KingstonStudent
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.ExecutionContext

class KingstonStudentSchema @Inject()(protected val dbConfigProvider:DatabaseConfigProvider)
                                     (implicit executionContext:ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  class kingstonStudentTable (tag:Tag) extends Table[KingstonStudent](tag,"Ku_student"){
    def nickname = column [String]("nickname")
    def email = column[String]("email")
    def subject= column[String]("subject")
    def typeOfStudy = column[String]("typeOfStudy")
    def password = column[String]("password")
    def fromKingston = column[Boolean]("from_Kingston")
    def * = (nickname,email,subject,typeOfStudy,password,fromKingston) <> ((KingstonStudent.apply _).tupled, KingstonStudent.unapply)
  }

  val Kstudents = TableQuery[kingstonStudentTable]




}
