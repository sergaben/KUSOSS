package controllers

import database.KingstonStudentRepositoryImpl
import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.ExecutionContext

/**
  *
  * @project Backend
  * @author sergaben on 08/04/2018.
  *
  */
class logOut @Inject()(cc:ControllerComponents, kingstonStudentRepositoryImpl: KingstonStudentRepositoryImpl)
                     (implicit executionContext:ExecutionContext) extends AbstractController(cc){

}
