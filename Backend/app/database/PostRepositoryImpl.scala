package database
import database.Schemas.PostSchema
import javax.inject.Inject
import models.Intefaces.IPostRepository
import models.Post
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.basic.DatabasePublisher
import slick.jdbc.{JdbcProfile, ResultSetConcurrency, ResultSetType}

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



  override def add(post: Post): Future[Option[Int]] = {
    val insertQuery =  posts returning posts.map(_.id) += post
//    val seqOfQuery = DBIO.seq(inserts)
    db.run(insertQuery.transactionally)
  }

  override def update(post: Post): Future[Unit] = ???

  override def delete(post: Post): Future[Unit] = ???

  override def getAllPostsBySubject(subject:String): DatabasePublisher[Post] = {
    val q = posts.filter(_.relatedSubject === subject)
    db.stream(
      q
        .result
        .withStatementParameters(
          rsType = ResultSetType.ForwardOnly,
          rsConcurrency = ResultSetConcurrency.ReadOnly,
          fetchSize = 10)
        .transactionally
    )
  }

  override def getById(id: Int): Future[Option[Post]] = ???
}
