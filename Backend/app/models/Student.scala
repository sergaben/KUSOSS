package models

// abstract class related to students
abstract class Student {
  val id:Int
  val nickname:String
  val password:String
  val email:String
  val subject:Subject
  val chatRoom:Seq[ChatRoom]
  val typeOfStudy:TypeOfStudy
}
