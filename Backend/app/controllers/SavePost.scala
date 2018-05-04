package controllers

import database.PostRepository
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


class SavePost @Inject()(cc:ControllerComponents, postRepository: PostRepository)
                        (implicit executionContext:ExecutionContext) extends AbstractController(cc){

  def savePost: Action[Post] = Action.async(parse.json[Post]) { implicit req =>
    val postFromFrontEnd = req.body
    println(postFromFrontEnd)
    getFutureToSavePost(postRepository.add(postFromFrontEnd))(n => Future.successful(Ok(Json.obj("status" -> "OK", "saved" -> true, "inserted" -> n))))

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
