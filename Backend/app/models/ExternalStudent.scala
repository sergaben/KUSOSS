package models

import java.sql.Timestamp

case class ExternalStudent(expirationOfUser: Timestamp) extends Student{
  val id = 123
  val nickname = "fasfsd"
  val password = "fsdafs"
  val email = "fasdf@faf"
  val chatRoom = Seq(ChatRoom(23,"fasdf",234))
  val subject = Subject(23,"fsadf",Course(24,"fasdf","fasdf"))
  val typeOfStudy = TypeOfStudy(34,"Undergraduate")
}

// a id for a student
// a nickname for the student
// a password for the student
// an email for the student
// to identify if the student is from kingston use of from_kingston
// to enable the expiration of the user after 5 days