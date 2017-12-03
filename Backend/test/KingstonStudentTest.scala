import models._
import org.scalatestplus.play.PlaySpec

class KingstonStudentTest extends PlaySpec{
  "A student " must {
    val nickname = "sergio"
    val password = "password"
    val email = "gkaso@hotmail.com"
    val subject = "Computer Science"
    val typeOfStudy = "Undergraduate"
    "have a nickname" in {
      val kStudent = KingstonStudent("sergio","fasdf","fasfsd","fasdf","fsadfsd")
      kStudent.nickname mustBe nickname
    }
    "have a password" in{
      val kStudent = KingstonStudent("sergio","password","afdsf","fasdf","fsadfsd")
      kStudent.password mustBe password
    }
    "have an email" in{
      val kStudent = KingstonStudent("sergio","password","gkaso@hotmail.com","Computer Science","Undergraduate")
      kStudent.email mustBe email
    }
    "have an subject" in{
      val kStudent = KingstonStudent("sergio","password","gkaso@hotmail.com","Computer Science","Undergraduate")
      kStudent.subject mustBe subject
    }
    "have an typeOfStudy" in{
      val kStudent = KingstonStudent("sergio","password","gkaso@hotmail.com","Computer Science","Undergraduate")
      kStudent.typeOfStudy mustBe typeOfStudy
    }

  }

}
