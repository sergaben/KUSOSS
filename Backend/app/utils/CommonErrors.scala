package utils
import play.api.mvc.{Result, Results}

// This code was taken from https://stackoverflow.com/questions/30351481/play-framework-how-to-implement-proper-error-handling

trait CommonErrors extends ErrorStack{
  case class NotFound(id:String) extends Exception(s"Object $id not found")
  case class InvalidId(id:String) extends Exception(s"$id is an invalid id")

  override def toResult(e: Exception): Result = e match {
    case e: NotFound => Results.NotFound(e.getMessage)
    case e: InvalidId => Results.BadRequest(e.getMessage)
    case _ => super.toResult(e)
  }
}

trait AuthErrors extends ErrorStack{
  case class UserNotFound(nickname:String) extends Exception(s"user $nickname not found")
  case class UserAlreadyExists(nickname:String) extends Exception(s"A user with the nickname: $nickname already exists")

  override def toResult(e: Exception): Result = e match{
    case e: UserNotFound => Results.NotFound(e.getMessage)
    case e:UserAlreadyExists => Results.BadRequest(e.getMessage)
    case _ => super.toResult(e)
  }
}

trait OtherErrors extends ErrorStack{
  case class AnotherError(s:String) extends Exception(s"Another error has occured: $s")

  override def toResult(e: Exception): Result = e match{
    case e: AnotherError => Results.BadRequest(e.getMessage)


    case _ => super.toResult(e)
  }
}
