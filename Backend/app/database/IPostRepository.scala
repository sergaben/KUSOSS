package database

import models.Post
import slick.basic.DatabasePublisher

import scala.concurrent.Future

/**
  *
  * @project Backend
  * @author sergaben on 23/04/2018.
  *
  */
trait IPostRepository {
  def add(post: Post): Future[Option[Int]]
  def getAllPostsBySubject(subject:String): DatabasePublisher[Post]
}
