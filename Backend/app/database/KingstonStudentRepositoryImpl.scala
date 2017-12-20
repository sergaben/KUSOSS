package database

import javax.inject.Inject

import database.Schemas.KingstonStudentSchema
import models.Intefaces.IKingstonStudentRepository
import models.KingstonStudent
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class KingstonStudentRepositoryImpl @Inject()(protected val dbConfigProvider:DatabaseConfigProvider,kingstonStudentSchema: KingstonStudentSchema)
                                             (implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] with IKingstonStudentRepository{
  import profile.api._


  private val KStudents = kingstonStudentSchema.Kstudents

  override def add(kingstonStudent: KingstonStudent) = {
    val inserts = KStudents += kingstonStudent
    val seqOfQuery = DBIO.seq(inserts)
    db.run(seqOfQuery)
  }

  override def update(kingstonStudent: KingstonStudent) = ???

  override def delete(kingstonStudent: KingstonStudent) = ???

  override def getAll() : Future[Seq[KingstonStudent]]= db.run(KStudents.result)

  override def getByNickname(nickname: String):Future[Option[KingstonStudent]] = {
    val q = KStudents.filter(_.nickname === nickname).result.headOption
    db.run(q)
  }
}
