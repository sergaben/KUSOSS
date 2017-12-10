package models.Intefaces

import models.Course

import scala.concurrent.Future

trait ICourseRepository {
  def add(course:Course):Future[Unit]
  def update(course: Course):Future[Unit]
  def delete(course: Course):Future[Unit]
  def getAll():Future[Seq[Course]]
  def getById(id:Int):Future[Option[Course]]
}
