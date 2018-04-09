package database

import java.util.UUID

import database.Schemas.KingstonStudentSchema
import javax.inject.Inject
import models.Intefaces.IKingstonStudentRepository
import models.KingstonStudent
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

  override def updateOrInsertToken(id:Option[Int],nickname: String,email:String,password:String,subject:String,typeOfStudy:String,loginToken:Option[String]): Future[Int] = {
    val tokenLoginAsOption:Option[String] = Option(UUID.randomUUID().toString)
    val getResult = for{
      existing <- KStudents.filter(_.nickname === nickname).result.headOption
      row      = existing.map(_.copy(loginToken=tokenLoginAsOption)) getOrElse KingstonStudent(id,nickname,email,password,subject,typeOfStudy,tokenLoginAsOption)
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
