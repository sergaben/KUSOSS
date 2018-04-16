package controllers

import database.PostRepositoryImpl
import javax.inject.Inject
import models.Post
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

/**
  *
  * @project Backend
  * @author sergaben on 15/04/2018.
  *
  */


class SavePost @Inject()(cc:ControllerComponents, postRepositoryImpl: PostRepositoryImpl)
                        (implicit executionContext:ExecutionContext) extends AbstractController(cc){

  def savePost = Action.async(parse.json[Post]) {implicit req =>
    val postFromFrontEnd = req.body
    println(postFromFrontEnd)
    getFutureToSavePost(postRepositoryImpl.add(postFromFrontEnd)){
      case 0 => Future.successful(Ok(Json.obj("Status"->"OK","saved"->true,"inserted"->0)))
      case n => Future.successful(Ok(Json.obj("Status"->"OK","saved"->true,"inserted"->n)))
    }

  }
  private def getFutureToSavePost[T](futureOptionBlock: Future[Option[T]])(foundBlock: (T => Future[Result])): Future[Result] = {
    futureOptionBlock.flatMap {
      case Some(found) =>
        foundBlock(found)
      case None =>
        Future.successful(Ok(Json.obj("status"->"OK","error"->"POST_NOT_SAVED")))
    }
  }
}
