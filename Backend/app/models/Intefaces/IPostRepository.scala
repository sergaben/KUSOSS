package models.Intefaces

import models.{Post}

import scala.concurrent.Future

/**
  *
  * @project Backend
  * @author sergaben on 11/04/2018.
  *
  */
trait IPostRepository {
  def add(post:Post):Future[Unit]
  def update(post:Post):Future[Unit]
  def delete(post:Post):Future[Unit]
  def getAll():Future[Seq[Post]]
  def getById(id:Int):Future[Option[Post]]
}
