package database

import database.Schemas.KingstonStudentSchema
import javax.inject.Inject
import models.Intefaces.IKingstonStudentRepository
import models.KingstonStudent
import org.joda.time.DateTime
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class KingstonStudentRepositoryImpl @Inject()(protected val dbConfigProvider:DatabaseConfigProvider,kingstonStudentSchema: KingstonStudentSchema)
                                             (implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] with IKingstonStudentRepository{
  import profile.api._

  private val KStudents = kingstonStudentSchema.Kstudents

  override def insert(kingstonStudent: KingstonStudent) = {
    val inserts = KStudents += kingstonStudent
    val seqOfQuery = DBIO.seq(inserts)
    db.run(seqOfQuery)
  }

  override def update(kingstonStudent: KingstonStudent) = ???

  override def delete(kingstonStudent: KingstonStudent) = ???

  override def updateOrInsertToken(nickname: String,email:String,password:String,fromKingston:Boolean,expirationTimeOfUser:Option[DateTime],subject:String,typeOfStudy:String,loginToken:Option[String]): Future[Int] = {
    val getResult = for{
      existing <- KStudents.filter(_.nickname === nickname).result.headOption
      row      = existing.map(_.copy(loginToken=loginToken)) getOrElse KingstonStudent(nickname,email,password,fromKingston,expirationTimeOfUser,subject,typeOfStudy,loginToken)
      result <- KStudents.insertOrUpdate(row)
    } yield result
    db.run(getResult)
  }
  override def getAll() : Future[Seq[KingstonStudent]]= db.run(KStudents.result)

  override def getByNickname(nickname: String):Future[Option[KingstonStudent]] = {
    val q = KStudents.filter(_.nickname === nickname ).result.headOption
    db.run(q)
  }

  override def getByEmail(email: String): Future[Option[KingstonStudent]] = {
    val query = KStudents.filter(_.email === email).result.headOption
    db.run(query)
  }

  override def auth(loginToken:String):Future[Option[KingstonStudent]] = {
    val query = KStudents.filter(_.loginToken === loginToken).result.headOption
    db.run(query)
  }

}
