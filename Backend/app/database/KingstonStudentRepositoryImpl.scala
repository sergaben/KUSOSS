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
    db.run(seqOfQuery.transactionally)
  }

  override def updateTokenToNull(nickname:String ) = {
    val query = for { k <- KStudents if k.nickname === nickname } yield k.loginToken
    val updateAction = query.update(None)
    db.run(updateAction)
  }

  override def delete(kingstonStudent: KingstonStudent) = ???

  override def updateOrInsertToken(id:Option[Int],nickname: String,email:String,password:String,subject:String,typeOfStudy:String,tokenToBeInserted:Option[String]): Future[Option[KingstonStudent]] = {
    val tokenLoginAsString:Option[String] = Option(UUID.randomUUID().toString)
//    println(tokenLoginAsString.getOrElse("NoToken"))
    val getResult = for{
      existing <- KStudents.filter(_.nickname === nickname ).result.headOption // this returns a kingston student or none
      row      = existing.map(_.copy(loginToken = tokenToBeInserted)) getOrElse KingstonStudent(id,nickname,email,password,subject,typeOfStudy,tokenLoginAsString) // this returns a kingston student with their corresponding token or create a new kingston student with a new token
      checkIfStudentHaveToken:String = row.loginToken.getOrElse("null") // this gets the token from the previous row if there is no token we create a new one
      assignNewToken = if(checkIfStudentHaveToken.equals("null")) tokenLoginAsString else Option(checkIfStudentHaveToken)
      finalRow = row.copy(loginToken = assignNewToken) // this creates a new kingston student with the token saved in the previous variable
      result <- KStudents.returning(KStudents).insertOrUpdate(finalRow)// this insert or updates the row in the database
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

}
