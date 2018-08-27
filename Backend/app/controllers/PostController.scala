package controllers

import akka.actor.ActorSystem
import akka.stream.scaladsl.Source
import database.PostRepositoryImpl
import javax.inject.Inject
import models.Post
import play.api.http.ContentTypes
import play.api.libs.EventSource
import play.api.libs.EventSource.EventDataExtractor
import play.api.libs.json._
import play.api.mvc.WebSocket.MessageFlowTransformer
import play.api.mvc._

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future}

/**
  *
  * @project Backend
  * @author sergaben on 15/04/2018.
  *
  */


class PostController @Inject()(cc:ControllerComponents, postRepository: PostRepositoryImpl, actorSystem:ActorSystem)
                              (implicit executionContext:ExecutionContext) extends AbstractController(cc){


  case class GetPostsRequest(subject:String)

  implicit val postFormat: Post.PostFormat.type = Post.PostFormat
  implicit val messageFlowTransformer: MessageFlowTransformer[Post, Post] = MessageFlowTransformer.jsonMessageFlowTransformer[Post,Post]
  implicit val postEvents : EventDataExtractor[Post] = EventDataExtractor(postFormat.writes(_).toString())
  implicit val GetPostsRequestReads: Reads[GetPostsRequest] = (JsPath \ "subject").read[String].map(GetPostsRequest.apply _)
  implicit val GetPostsRequestWrites: Writes[GetPostsRequest] {
    def writes(getPostsRequest: GetPostsRequest): JsObject
  } = new Writes[GetPostsRequest] {
    def writes(getPostsRequest: GetPostsRequest): JsObject = Json.obj(
      "subject" -> getPostsRequest.subject
    )
  }

  def getPosts(subject:String): Action[AnyContent] = Action.async{ implicit req=>

    def postSource = Source.fromPublisher(postRepository.getAllPostsBySubject(subject))
    val sourceWithTick = Source.tick(1.second, 1.second , 0)
    val postFlow = postSource.zip(sourceWithTick).map(_._1) via EventSource.flow[Post] //zip guarantees the order of the elements in the stream and waits until all the elements are available

    Future.successful(Ok.chunked(postFlow).as(ContentTypes.EVENT_STREAM))
  }

  def savePost: Action[Post] = Action.async(parse.json[Post]) { implicit req =>
    val post = req.body
    val postSaved : Future[Option[Int]] = postRepository.add(post)
    val postSavedAsResult : Future[Result] = postSaved.flatMap{
      case Some(n) =>
        Future.successful(Ok(Json.obj("status" -> "OK", "saved" -> true, "inserted" -> n)))
      case None =>
        Future.successful(Ok(Json.obj("status" -> "OK", "error" -> "POST_NOT_SAVED")))
    }
    postSavedAsResult
}

//  private def getFutureToSavePost[T](futureOptionBlock: Future[Option[T]])(foundBlock: T => Future[Result]): Future[Result] = {
//    futureOptionBlock.flatMap {
//      case Some(found) =>
//        foundBlock(found)
//      case None =>
//        Future.successful(Ok(Json.obj("status"->"OK","error"->"POST_NOT_SAVED")))
//    }
//  }
}
