package database

import javax.inject.Inject

import database.Schemas.SubjectSchema
import models.Intefaces.ISubjectRepository
import models.Subject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.ExecutionContext

class SubjectRepositoryImpl @Inject()(protected val dbConfigProvider:DatabaseConfigProvider,subjectSchema:SubjectSchema)
                                     (implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] with ISubjectRepository{
  import profile.api._

  private val subjects = TableQuery[subjectSchema.SubjectTable]

  override def getAll() = db.run(subjects.result)

  override def add(subject: Subject) = ???

  override def update(subject: Subject) = ???

  override def delete(subject: Subject) = ???

  override def getById(id: Int) = ???
}
