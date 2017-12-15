package database.Schemas

import javax.inject.Inject

import com.github.tototoshi.slick.PostgresJodaSupport._
import models.KingstonStudent
import org.joda.time.DateTime
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.ExecutionContext

class KingstonStudentSchema @Inject()(protected val dbConfigProvider:DatabaseConfigProvider)
                                     (implicit executionContext:ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  class kingstonStudentTable (tag:Tag) extends Table[KingstonStudent](tag,"Ku_student"){
    def nickname = column [String]("nickname")
    def email = column[String]("email")
    def password = column[String]("password")
    def fromKingston = column[Boolean]("from_Kingston")
    def expirationTimeOfUser = column[Option[DateTime]]("Expiration_time_of_user")
    def subject= column[String]("subject")
    def typeOfStudy = column[String]("typeOfStudy")


    def * = (nickname,email,password,fromKingston,expirationTimeOfUser,subject,typeOfStudy) <> ((KingstonStudent.apply _).tupled, KingstonStudent.unapply _)
  }


  val Kstudents = TableQuery[kingstonStudentTable]




}
