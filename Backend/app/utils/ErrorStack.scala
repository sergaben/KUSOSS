package utils

import play.api.mvc.{Result, Results}

// This code was taken from https://stackoverflow.com/questions/30351481/play-framework-how-to-implement-proper-error-handling
trait ErrorStack {
  def toResult(e: Exception): Result = e match{
    case _ => Results.InternalServerError(e.getMessage)
  }
}
