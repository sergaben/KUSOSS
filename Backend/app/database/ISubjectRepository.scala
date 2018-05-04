package database

import models.Subject

import scala.concurrent.Future

/**
  *
  * @project Backend
  * @author sergaben on 23/04/2018.
  *
  */
trait ISubjectRepository {

  def getAll:Future[Seq[Subject]]
  def delete(subject: Subject):Future[Unit]

}
