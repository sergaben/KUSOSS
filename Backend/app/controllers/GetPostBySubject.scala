package controllers



import akka.actor.ActorSystem
import akka.http.scaladsl.model.HttpEntity.ChunkStreamPart
import akka.http.scaladsl.model.{HttpEntity, HttpResponse, MediaTypes}
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source
import database.PostRepositoryImpl
import javax.inject.Inject
import play.api.libs.json.Json
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

  def getPosts: Action[AnyContent] = Action.async{ implicit req=>
    println(req.body.asText.getOrElse("no subject"))
    val postSource = Source.fromPublisher(postRepositoryImpl.getAllPostsBySubject(req.body.asText.getOrElse("no subject")))
    postSource.runForeach(post => println(post.toString))(materializer)
    Future.successful(Ok(Json.obj("status"->"OK","stream"->"OK")))

  }
}
