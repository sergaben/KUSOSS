package models

import java.sql.Date

import play.api.libs.json._

case class Post (id:Option[Int], content: String, createdAt:Date, createdBy:Int, creatorNickname:String) {}

object Post{
  //This object is going to be used to format the class to json or from json to the class
  implicit object PostFormat extends Format[Post] {


    def reads(json: JsValue): JsResult[Post] = {
      // the Vals below hold all the parameters for the KingstonStudent class
      val id = (json \ "id").asOpt[Int]
      val content = (json \ "content").as[String]
      val createdAt = (json \ "created_at").as[Date]
      val createdBy = (json \ "created_by").as[Int]
      val creatorNickname = (json \ "creator_nickname").as[String]
      // The line below returns a KingstonStudent object
      JsSuccess(Post(id,content, createdAt,createdBy, creatorNickname))
    }

    def writes(post: Post): JsValue = {
      // the Seq below creates a json object
      val postAsList = Seq(
        "id" -> Json.toJson(post.id),
        "content" -> JsString(post.content),
        "created_at" -> Json.toJson(post.createdAt),
        "created_by" -> JsNumber(post.createdBy),
        "creator_nickname" -> JsString(post.creatorNickname)
      )
      //The line below returns a json object
      JsObject(postAsList)
    }
  }
}