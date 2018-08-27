package database

import java.util.UUID

import database.Schemas.StudentSchema
import javax.inject.Inject
import models.Student
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class StudentRepositoryImpl @Inject()(protected val dbConfigProvider:DatabaseConfigProvider, kingstonStudentSchema: StudentSchema)
                                     (implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] with  StudentRepository {
  import profile.api._

  private val KStudents = kingstonStudentSchema.Students

   override def insert(kingstonStudent: Student): Future[Unit] = {
    val inserts = KStudents += kingstonStudent
    val seqOfQuery = DBIO.seq(inserts)
    db.run(seqOfQuery.transactionally)
  }

  override def updateTokenToNull(nickname:String ): Future[Int] = {
    val query = for { k <- KStudents if k.nickname === nickname } yield k.loginToken
    val updateAction = query.update(None)
    db.run(updateAction)
  }


  override def updateOrInsertToken(id:Option[Int],nickname: String,email:String,password:String,subject:String,typeOfStudy:String,tokenToBeInserted:Option[String]): Future[Option[Student]] = {
    val tokenLoginAsString:Option[String] = Option(UUID.randomUUID().toString)
//    println(tokenLoginAsString.getOrElse("NoToken"))
    val getResult = for{
      existing <- KStudents.filter(_.nickname === nickname ).result.headOption // this returns a kingston student or none
      row      = existing.map(_.copy(loginToken = tokenToBeInserted)) getOrElse Student(id,nickname,email,password,subject,typeOfStudy,tokenLoginAsString) // this returns a kingston student with their corresponding token or create a new kingston student with a new token
      checkIfStudentHaveToken:String = row.loginToken.getOrElse("null") // this gets the token from the previous row if there is no token we create a new one
      assignNewToken = if(checkIfStudentHaveToken.equals("null")) tokenLoginAsString else Option(checkIfStudentHaveToken)
      finalRow = row.copy(loginToken = assignNewToken) // this creates a new kingston student with the token saved in the previous variable
      result <- KStudents.returning(KStudents).insertOrUpdate(finalRow)// this insert or updates the row in the database
    } yield result
    db.run(getResult)
  }

  override def getAll: Future[Seq[Student]]= db.run(KStudents.result)

  override def getByNickname(nickname: String):Future[Option[Student]] = {
    val q = KStudents.filter(_.nickname === nickname ).result.headOption
    db.run(q)
  }

  override def getByEmail(email: String): Future[Option[Student]] = {
    val query = KStudents.filter(_.email === email).result.headOption
    db.run(query)
  }

}
