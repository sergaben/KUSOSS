package controllers

import database.KingstonStudentRepositoryImpl
import javax.inject.Inject
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import scala.concurrent.{ExecutionContext, Future}

/**
  *
  * @project Backend
  * @author sergaben on 08/04/2018.
  *
  */
class LogOut @Inject()(cc:ControllerComponents, kingstonStudentRepositoryImpl: KingstonStudentRepositoryImpl)
                     (implicit executionContext:ExecutionContext) extends AbstractController(cc){
  //TODO - Add logout functionality, checking the current token and if is in the database update it to null and do the same with the local storage in frontend

  def logout: Action[AnyContent] = Action.async{ implicit req =>
    Future.successful(Ok(req.body.asJson.get.toString()))
  }
}
