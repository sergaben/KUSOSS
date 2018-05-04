package controllers



import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source
import database.PostRepository
import javax.inject.Inject
import models.Post
import play.api.http.ContentTypes
import play.api.libs.EventSource
import play.api.libs.EventSource.EventDataExtractor
import play.api.libs.json._
import play.api.mvc.WebSocket.MessageFlowTransformer
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future}

/**
  *
  * @project Backend
  * @author sergaben on 16/04/2018.
  *
  */
class GetPostBySubject @Inject()(cc:ControllerComponents, postRepository: PostRepository,actorSystem:ActorSystem,actorMaterializer: ActorMaterializer)
                                (implicit executionContext:ExecutionContext) extends AbstractController(cc) {
  implicit val postFormat: Post.PostFormat.type = Post.PostFormat
  implicit val messageFlowTransformer: MessageFlowTransformer[Post, Post] = MessageFlowTransformer.jsonMessageFlowTransformer[Post,Post]
  implicit val postEvents : EventDataExtractor[Post] = EventDataExtractor(postFormat.writes(_).toString())

  case class GetPostsRequest(subject:String)

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

}
