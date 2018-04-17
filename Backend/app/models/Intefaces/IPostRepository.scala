package models.Intefaces

import models.Post
import slick.basic.DatabasePublisher

import scala.concurrent.Future

/**
  *
  * @project Backend
  * @author sergaben on 11/04/2018.
  *
  */
trait IPostRepository {
  def add(post:Post):Future[Option[Int]]
  def update(post:Post):Future[Unit]
  def delete(post:Post):Future[Unit]
  def getAllPostsBySubject(subject:String):DatabasePublisher[Post]
  def getById(id:Int):Future[Option[Post]]
}
