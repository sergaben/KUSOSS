import models.ChatRoom

abstract class Student {
  val id:Int
  val nickname:String
  val password:String
  val email:String
  val chatRoom:Seq[ChatRoom]
}

case class KingstonStudent(id:Int,nickname:String,password:String,email:String,chatRoom: Seq[ChatRoom]) extends Student

val chatRoom1 = ChatRoom(23,"fasfd",2)
val chatRoom2 = ChatRoom(24,"foirk",4)

val newStudent = KingstonStudent(123,"fsadf","fsafsd","fadsfsdf",Seq(chatRoom1,chatRoom2))

