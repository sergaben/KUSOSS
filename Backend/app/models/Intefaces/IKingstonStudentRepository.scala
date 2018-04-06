package models.Intefaces

import models.KingstonStudent

import scala.concurrent.Future

trait IKingstonStudentRepository {

  def insert(kingstonStudent:KingstonStudent):Future[Unit]
  def update(kingstonStudent:KingstonStudent):Future[Unit]
  def delete(kingstonStudent:KingstonStudent):Future[Unit]
  def updateToken(nickname: String, loginToken:Option[String]):Future[Int]
  def getAll():Future[Seq[KingstonStudent]]
  def getByNickname(nickname:String):Future[Option[KingstonStudent]]
  def getByEmail(email:String):Future[Option[KingstonStudent]]
  def auth(loginToken:String):Future[Option[KingstonStudent]]
}
