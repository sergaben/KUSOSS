package database
import database.Schemas.{KingstonStudentSchema, PostSchema}
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
class PostRepositoryImpl @Inject()(protected val dbConfigProvider:DatabaseConfigProvider,postSchema: PostSchema)
                                  (implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] with IPostRepository{

  import profile.api._

  private val posts = postSchema.posts

  override def add(post: Post): Future[Unit] = {
    val inserts = posts += post
    val seqOfQuery = DBIO.seq(inserts)
    db.run(seqOfQuery.transactionally)
  }

  override def update(post: Post): Future[Unit] = ???

  override def delete(post: Post): Future[Unit] = ???

  override def getAll(): Future[Seq[Post]] = ???

  override def getById(id: Int): Future[Option[Post]] = ???
}
