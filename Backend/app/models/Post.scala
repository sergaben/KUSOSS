package models

import org.joda.time.DateTime
import play.api.libs.json._

case class Post (id:Option[Int], content: String, createdAt:DateTime, createdBy:Int, creatorNickname:String, relatedSubject:String) {}

//DONE - add type of post by subject

object Post{
  //This object is going to be used to format the class to json or from json to the class
  implicit object PostFormat extends Format[Post] {

    implicit val dateTimeWriter : Writes[DateTime] = JodaWrites.jodaDateWrites("dd-MM-yyyy'T'HH:mm:ss.SSSZ")
    implicit val dateTimeJsReader: Reads[DateTime] = JodaReads.jodaDateReads("dd-MM-yyyy HH:mm:ss")

    def reads(json: JsValue): JsResult[Post] = {
      // the Vals below hold all the parameters for the KingstonStudent class
      val id = (json \ "id").asOpt[Int]
      val content = (json \ "content").as[String]
      val createdAt = (json \ "created_at").as[DateTime]
      val createdBy = (json \ "created_by").as[Int]
      val creatorNickname = (json \ "creator_nickname").as[String]
      val relatedSubject = (json \ "related_subject").as[String]
      // The line below returns a KingstonStudent object
      JsSuccess(Post(id,content, createdAt,createdBy, creatorNickname,relatedSubject))
    }

    def writes(post: Post): JsValue = {
      // the Seq below creates a json object
      val postAsList = Seq(
        "id" -> Json.toJson(post.id),
        "content" -> JsString(post.content),
        "created_at" -> Json.toJson(post.createdAt),
        "created_by" -> JsNumber(post.createdBy),
        "creator_nickname" -> JsString(post.creatorNickname),
        "related_subject" -> JsString(post.relatedSubject)
      )
      //The line below returns a json object
      JsObject(postAsList)
    }


  }
}