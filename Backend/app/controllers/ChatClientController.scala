package controllers

import java.util.Calendar
import javax.inject.Inject

import models.{KingstonStudent, Post}
import play.api.Logger
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

  def connectionBetweenBackendFrontend(someText:String)= Action {
      Ok(someText)
  }

}
