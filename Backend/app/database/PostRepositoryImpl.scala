package database
import database.Schemas.KingstonStudentSchema
import javax.inject.Inject
import models.Intefaces.IPostRepository
import models.Post
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}
/**
  *
  * @project Backend
  * @author sergaben on 13/04/2018.
  *
  */
class PostRepositoryImpl @Inject()(protected val dbConfigProvider:DatabaseConfigProvider,kingstonStudentSchema: KingstonStudentSchema)
                                  (implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] with IPostRepository{

  override def add(post: Post): Future[Unit] = ???

  override def update(post: Post): Future[Unit] = ???

  override def delete(post: Post): Future[Unit] = ???

  override def getAll(): Future[Seq[Post]] = ???

  override def getById(id: Int): Future[Option[Post]] = ???
}
