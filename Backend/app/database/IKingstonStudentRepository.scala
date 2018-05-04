package database

import models.KingstonStudent
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.Future

/**
  *
  * @project Backend
  * @author sergaben on 23/04/2018.
  *
  */
trait IKingstonStudentRepository {

  def insert(kingstonStudent: KingstonStudent): Future[Unit]
  def updateTokenToNull(nickname:String ): Future[Int]
  def updateOrInsertToken(id:Option[Int],nickname: String,email:String,password:String,subject:String,typeOfStudy:String,tokenToBeInserted:Option[String]): Future[Option[KingstonStudent]]
  def getAll: Future[Seq[KingstonStudent]]
  def getByNickname(nickname: String):Future[Option[KingstonStudent]]
  def getByEmail(email: String): Future[Option[KingstonStudent]]

}
