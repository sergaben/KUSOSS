package database

import database.Schemas.SubjectSchema
import javax.inject.Inject
import models.Subject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}


class SubjectRepository @Inject()(protected val dbConfigProvider:DatabaseConfigProvider, subjectSchema:SubjectSchema)
                                 (implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile]{

  import profile.api._

  private val subjects = subjectSchema.subjects


   def delete(subject: Subject)= ???

   def getAll:Future[Seq[Subject]] = db.run(subjects.result)
}
