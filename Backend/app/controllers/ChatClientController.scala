package controllers

import javax.inject.Inject

import play.api.libs.ws.WSClient
import play.api.mvc._

class ChatClientController @Inject()(cc:ControllerComponents,ws:WSClient)()extends AbstractController(cc){

//
//  def connectionBetweenBackendFrontend= Action(parse.json) { req =>
//      val post = req.body.validate[Post]
//      post.fold(
//        errors => {
//          BadRequest(Json.obj("Status"->"KO","Message"->JsError.toJson(errors)))
//        },
//        post => {
//          Ok(Json.obj("Status"->"OK","message"->("Post' content: "+post.content)))
//        }
//      )
//  }
}
