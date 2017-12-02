package models

// simple case class for a student
case class KingstonStudent(posts:Seq[Post]) extends Student{
  val id = 2343
  val nickname = "fsafds"
  val password = "fasdfsadf"
  val email = "fasdf@fasdfs"
  val chatRoom = Seq(ChatRoom(23,"fasdf",234))
  val subject = Subject(12,"fasfsd",Course(234,"fasdfs","Afsdf"))
  val typeOfStudy = TypeOfStudy(34,"Undergraduate")

  def selectOnePost(idStudent:Int):Any = {
    posts.map(Post=>
      if(Post.studentId.equals(idStudent)){
         Post
      }
    )
  }
}



// a id for a student
// a nickname for the student
// a password for the student
// an email for the student

