package models.Intefaces

import models.Subject

import scala.concurrent.{ExecutionContext, Future}

trait ISubjectRepository {
  def add(subject:Subject)(implicit e:ExecutionContext):Future[Unit]
  def update(subject:Subject)(implicit e:ExecutionContext):Future[Unit]
  def delete(subject:Subject)(implicit e:ExecutionContext):Future[Unit]
  def getAll()(implicit e:ExecutionContext):Future[Seq[Subject]]
  def getById(id:Int)(implicit e:ExecutionContext):Future[Option[Subject]]
}
