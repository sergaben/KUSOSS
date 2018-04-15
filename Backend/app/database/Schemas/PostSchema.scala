package database.Schemas

import java.sql.{Date, Timestamp}

import javax.inject.Inject
import models.Post
import org.joda.time.DateTime
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.ExecutionContext

/**
  *
  * @project Backend
  * @author sergaben on 11/04/2018.
  *
  */
class PostSchema @Inject()(protected val dbConfigProvider:DatabaseConfigProvider)
                          (implicit executionContext:ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  implicit def dateTime =
    MappedColumnType.base[DateTime, Timestamp](
      dt => new Timestamp(dt.getMillis),
      ts => new DateTime(ts.getTime)
    )

  class PostTable (tag:Tag) extends Table[Post](tag, "Post"){
    def id = column[Option[Int]]("id",O.PrimaryKey,O.AutoInc)
    def content = column[String]("Content")
    def createdAt = column[DateTime]("Created_at")
    def createdBy = column[Int]("Created_by")
    def creatorNickname = column[String]("Creator_nickname")
    def relatedSubject = column[String]("related_subject")
    def * =(id,content,createdAt,createdBy,creatorNickname,relatedSubject) <> ((Post.apply _).tupled, Post.unapply _)
  }

  val posts = TableQuery[PostTable]


}
