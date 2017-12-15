package controllers

import javax.inject.Inject

import database.KingstonStudentRepositoryImpl
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.ExecutionContext

class Login @Inject()(cc:ControllerComponents, kingstonStudentRepositoryImpl: KingstonStudentRepositoryImpl)
                     (implicit executionContext:ExecutionContext) extends AbstractController(cc){

  def login = Action(parse.json){ req =>
    // get the nickname and password from frontend
    // get the nickname and password from database
    // check that the password matched the one in the database
    // if it matches then send true otherwise send false
    val nickname = (req.body \ "nickname").as[String]
    val password = (req.body \ "matchPassword").as[String]
    println(nickname,password)
    Ok
  }

}
