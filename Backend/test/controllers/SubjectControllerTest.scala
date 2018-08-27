package controllers

import akka.stream.Materializer
import database.SubjectRepositoryImpl
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import org.specs2.mutable.Specification
import play.api.libs.json.Json
import play.api.mvc._
import play.api.test.{FakeRequest, Helpers}
import play.api.test.Helpers.{CONTENT_TYPE, GET, call, contentAsJson}
import scala.concurrent.ExecutionContext.Implicits.global
/**
  *
  * @project Backend
  * @author sergaben on 20/04/2018.
  *
  */
class SubjectControllerTest(cc:ControllerComponents, subjectRepositoryImpl: SubjectRepositoryImpl) extends PlaySpec with GuiceOneAppPerSuite with Results {

  "GetSubjectsTest" should {
    "getSubjectsNamesAsJson" in {
      implicit lazy val materializer: Materializer = app.materializer
      val stubParser = Helpers.stubBodyParser(AnyContent("A subject"))
      val action: EssentialAction  = Action { request =>
        val value = (request.body.asJson.get \ "field").as[String]
        Ok(value)
      }
      val body = Json.obj("name"->"subjectName")
      val controller = new SubjectController(Helpers.stubControllerComponents(),subjectRepositoryImpl:SubjectRepositoryImpl)
      val result = call(controller.getSubjectsNamesAsJson(),FakeRequest(GET,"/getSubjectsNamesAsJson ").withHeaders((CONTENT_TYPE,"application/json")).withJsonBody(body))
      contentAsJson(result) mustBe body
    }

  }
}
