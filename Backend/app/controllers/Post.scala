package controllers

import database.{KingstonStudentRepositoryImpl, PostRepositoryImpl}
import javax.inject.Inject
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import scala.concurrent.{ExecutionContext, Future}

/**
  *
  * @project Backend
  * @author sergaben on 15/04/2018.
  *
  */
class Post @Inject()(cc:ControllerComponents,postRepositoryImpl: PostRepositoryImpl)
                    (implicit executionContext:ExecutionContext) extends AbstractController(cc){

  def savePost: Action[AnyContent] = Action.async{ implicit req =>
    println(req.body)
    Future.successful(Ok(Json.obj("Status"->"OK")))
  }
}
