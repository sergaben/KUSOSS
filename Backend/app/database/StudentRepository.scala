package database

import models.Student

import scala.concurrent.Future

/**
  *
  * @project Backend
  * @author sergaben on 23/04/2018.
  *
  */
trait StudentRepository {

  def insert(kingstonStudent: Student): Future[Unit]
  def updateTokenToNull(nickname:String ): Future[Int]
  def updateOrInsertToken(id:Option[Int],nickname: String,email:String,password:String,subject:String,typeOfStudy:String,tokenToBeInserted:Option[String]): Future[Option[Student]]
  def getAll: Future[Seq[Student]]
  def getByNickname(nickname: String):Future[Option[Student]]
  def getByEmail(email: String): Future[Option[Student]]

}
