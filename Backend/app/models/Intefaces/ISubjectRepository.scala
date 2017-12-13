package models.Intefaces

import models.Subject

import scala.concurrent.Future

trait ISubjectRepository {
  def add(subject:Subject):Future[Unit]
  def update(subject:Subject):Future[Unit]
  def delete(subject:Subject):Future[Unit]
  def getAll():Future[Seq[Subject]]
  def getById(id:Int):Future[Option[Subject]]
}
