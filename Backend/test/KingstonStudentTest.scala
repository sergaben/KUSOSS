import java.util.Calendar

import models._
import org.scalatestplus.play.PlaySpec

class KingstonStudentTest extends PlaySpec{
  "A student " must {
    "have a nickname" in {
      val course = Course(234,"fasdf","fasdf")
      val subject = Subject(234,"fasdf",course)
      val typeOfStudy = TypeOfStudy(87,"fasdf")
      val chatRoom = ChatRoom(34,"fasdf",4)
      val kingstonStudent = KingstonStudent(Seq(Post(23,8989,"fadsf adf as",Calendar.getInstance.getTime())))
      kingstonStudent.nickname mustBe "fasfds"
    }

  }

 // 8989,"fasfds","dioser","fasdfd",subject,Seq(chatRoom),typeOfStudy,
}
