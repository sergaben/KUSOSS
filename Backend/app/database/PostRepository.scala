package database
import database.Schemas.PostSchema
import javax.inject.Inject
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
class PostRepository @Inject()(protected val dbConfigProvider:DatabaseConfigProvider, postSchema: PostSchema)
                              (implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] with IPostRepository {

  import profile.api._

  private val posts = postSchema.posts


  override def add(post: Post): Future[Option[Int]] = {
    val insertQuery =  posts returning posts.map(_.id) += post
    db.run(insertQuery.transactionally)
  }
   override def getAllPostsBySubject(subject:String): DatabasePublisher[Post] = {
    val q = posts.filter(_.relatedSubject === subject)
    db.stream(
      q
        .result
        .withStatementParameters(
          rsType = ResultSetType.ForwardOnly, // meaning that it only reads rows in one direction https://www.cs.mun.ca/java-api-1.5/guide/jdbc/getstart/resultset.html
          rsConcurrency = ResultSetConcurrency.ReadOnly, // avoids the update of the row https://docs.oracle.com/javase/tutorial/jdbc/basics/retrieving.html
          fetchSize = 1) // how many rows to fetch from database https://stackoverflow.com/questions/1318354/what-does-statement-setfetchsizensize-method-really-do-in-sql-server-jdbc-driv
        .transactionally
    ).mapResult(row=>row)
  }
}
