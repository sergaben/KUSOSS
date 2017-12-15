package models.Intefaces

import models.KingstonStudent

import scala.concurrent.Future

trait IKingstonStudentRepository {

  def add(kingstonStudent:KingstonStudent):Future[Unit]
  def update(kingstonStudent:KingstonStudent):Future[Unit]
  def delete(kingstonStudent:KingstonStudent):Future[Unit]
  def getAll():Future[Seq[KingstonStudent]]
  def getByNickname(nickname:String):Future[KingstonStudent]

}
