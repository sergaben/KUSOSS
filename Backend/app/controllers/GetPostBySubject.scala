package controllers



import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source
import database.PostRepositoryImpl
import javax.inject.Inject
import models.Post
import play.api.http.ContentTypes
import play.api.libs.EventSource
import play.api.libs.EventSource.EventDataExtractor
import play.api.libs.json._
import play.api.mvc.WebSocket.MessageFlowTransformer
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import scala.concurrent.{ExecutionContext, Future}

/**
  *
  * @project Backend
  * @author sergaben on 16/04/2018.
  *
  */
class GetPostBySubject @Inject()(cc:ControllerComponents, postRepositoryImpl: PostRepositoryImpl)
                                (implicit executionContext:ExecutionContext) extends AbstractController(cc) {
  implicit val postFormat = Post.PostFormat
  implicit val system = ActorSystem("QuickStart")
  implicit val materializer = ActorMaterializer()
  implicit val messageFlowTransformer = MessageFlowTransformer.jsonMessageFlowTransformer[Post,Post]
  implicit val postEvents : EventDataExtractor[Post] = EventDataExtractor(postFormat.writes(_).toString())
  case class GetPostsRequest(subject:String)

  implicit val GetPostsRequestReads: Reads[GetPostsRequest] = (JsPath \ "subject").read[String].map(GetPostsRequest.apply _)

  implicit val GetPostsRequestWrites = new Writes[GetPostsRequest] {
    def writes(getPostsRequest: GetPostsRequest): JsObject = Json.obj(
      "subject" -> getPostsRequest.subject
    )
  }
  private def validateJson[A:Reads] = parse.json.validate(
    _.validate[A].asEither.left.map(e => BadRequest(JsError.toJson(e)))
  )


  def getPosts(subject:String): Action[AnyContent] = Action.async{ implicit req=>
//    println(req.body.subject)
    println(subject)
    val postSource = Source.fromPublisher(postRepositoryImpl.getAllPostsBySubject(subject))
    Future.successful(Ok.chunked(postSource via EventSource.flow[Post].limit(50)).as(ContentTypes.EVENT_STREAM))
  }
}
