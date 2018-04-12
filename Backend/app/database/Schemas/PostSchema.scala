package database.Schemas

import javax.inject.Inject
import models.Post
import org.joda.time.DateTime
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.lifted.{TableQuery, Tag}

import scala.concurrent.ExecutionContext

/**
  *
  * @project Backend
  * @author sergaben on 11/04/2018.
  *
  */
class PostSchema @Inject()(protected val dbConfigProvider:DatabaseConfigProvider)
                          (implicit executionContext:ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile._

  class PostStudent (tag:Tag) extends Table[Post](tag, "Post"){
    def id = column[Option[Int]]("id",O.PrimaryKey,O.AutoInc)
    def created_at = column[DateTime]("Created_at")
    def content = column[String]("Content")
    def createdBy = column[Int]("Created_by")
    def creatorNickname = column[Int]("Creator_nickname")
    def * =(id,created_at,content,createdBy,creatorNickname) <> ((Post.apply _).tupled, Post.unapply _)
  }

  val posts = TableQuery[Post]


}
