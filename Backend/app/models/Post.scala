package models

import java.util.Date

case class Post (id:Option[Int],content: String, createdAt:Date, createdBy:Int, creatorNickname:String) {}
