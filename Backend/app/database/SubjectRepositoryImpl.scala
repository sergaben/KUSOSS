package database

import database.Schemas.SubjectSchema
import javax.inject.Inject
import models.Subject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}


class SubjectRepositoryImpl @Inject()(protected val dbConfigProvider:DatabaseConfigProvider, subjectSchema:SubjectSchema)
                                     (implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] with SubjectRepository {

  import profile.api._

  private val subjects = subjectSchema.subjects


   override def delete(subject: Subject): Future[Unit] = ???

   override def getAll:Future[Seq[Subject]] = db.run(subjects.result)
}
