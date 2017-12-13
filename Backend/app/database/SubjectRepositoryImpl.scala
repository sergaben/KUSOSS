package database

import javax.inject.Inject

import database.Schemas.SubjectSchema
import models.Intefaces.ISubjectRepository
import models.Subject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

// A repository implementation of all the possible queries that can be run in the Subject table
class SubjectRepositoryImpl @Inject()(protected val dbConfigProvider:DatabaseConfigProvider,subjectSchema:SubjectSchema)
                                     (implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] with ISubjectRepository{

  import profile.api._

  private val subjects = subjectSchema.subjects

  override def add(subject: Subject) = ???

  override def update(subject: Subject) = ???

  override def delete(subject: Subject)= ???

  override def getAll() :Future[Seq[Subject]] = db.run(subjects.result)

  override def getById(id: Int) = ???
}
