package controllers



import akka.actor.ActorSystem
import akka.http.scaladsl.model.HttpEntity.ChunkStreamPart
import akka.http.scaladsl.model.{HttpEntity, HttpResponse, MediaTypes}
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source
import database.PostRepositoryImpl
import javax.inject.Inject
import play.api.libs.json._
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
  implicit val system = ActorSystem("QuickStart")
  implicit val materializer = ActorMaterializer()

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


  def getPosts: Action[GetPostsRequest] = Action.async(validateJson[GetPostsRequest]){ implicit req=>
    println(req.body.subject)
    val postSource = Source.fromPublisher(postRepositoryImpl.getAllPostsBySubject(req.body.subject))
    postSource.runForeach(post => println(Json.toJson(post)))(materializer)
    Future.successful(Ok(Json.obj("status"->"OK","stream"->"OK")))
  }
}
