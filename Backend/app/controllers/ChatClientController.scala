package controllers

import java.util.{Calendar, Date}
import javax.inject.Inject

import models.{KingstonStudent, Post}
import play.api.Logger
import play.api.libs.json._
import play.api.libs.ws.WSClient
import play.api.mvc._

class ChatClientController @Inject()(cc:ControllerComponents,ws:WSClient)()extends AbstractController(cc){

  val postInstance = Post(23, 45, "fsadf",Calendar.getInstance.getTime)
  val student = KingstonStudent(Seq(Post(23,234,"fsadfsd",Calendar.getInstance().getTime)))
  val postOne = student.selectOnePost(postInstance.postId)

  Logger.debug(postOne.toString)


//  def connectionBetweenBackendFrontend1 = {
//    Action{ implicit request =>
//      ws.url("localhost:3000").
//      Ok(postOne.toString)
//    }
//  }
  implicit val postWrites: Reads[Post] = (
    (JsPath \ "postId").read[Int] and
    (JsPath \ "studentId").read[Int] and
    (JsPath \ "content").read[String] and
    (JsPath \ "createdAt").read[Date]
)(Post.apply _)

  def connectionBetweenBackendFrontend= Action(parse.json) { req =>
      val post = req.body.validate[Post]
      post.fold(
        errors => {
          BadRequest(Json.obj("Status"->"KO","Message"->JsError.toJson(errors)))
        },
        post => {
          Ok(Json.obj("Status"->"OK","message"->("Post' content: "+post.content)))
        }
      )
  }

}
