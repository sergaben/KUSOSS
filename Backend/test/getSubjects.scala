import akka.actor.ActorSystem
import akka.http.scaladsl.model.HttpHeader.ParsingResult.Ok
import akka.stream.{ActorMaterializer, Materializer}
import controllers.SubjectController
import database.SubjectRepositoryImpl
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.libs.json.Json
import play.api.mvc.EssentialAction
import play.api.mvc._
import play.api.test.{FakeRequest, Helpers}
import play.api.test.Helpers._



/**
  *
  * @project Backend
  * @author sergaben on 20/04/2018.
  *
  */
class getSubjects(cc:ControllerComponents, subjectRepositoryImpl: SubjectRepositoryImpl) extends PlaySpec with GuiceOneAppPerSuite with Results{
  "GetSubjects" should {
    "return valid subjects as Json" in {


    }
  }
}
