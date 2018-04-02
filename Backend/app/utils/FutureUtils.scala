package utils

import scala.concurrent.{ExecutionContext, Future}

/**
  *
  * @project Backend
  * @author sergaben on 02/04/2018.
  *
  */
object FutureUtils {
  class FutureProcessingException(msg: String) extends Exception(msg)
  class MissingOptionValueException(msg: String) extends FutureProcessingException(msg)

  protected final class OptionFutureToOptionValueFuture[T](f: Future[Option[T]]) {
    def whenUndefined(error: String)(implicit context: ExecutionContext): Future[T] = {
      f.map { value =>
        if (value.isDefined) value.get else throw new MissingOptionValueException(error)
      }
    }
  }

  import scala.language.implicitConversions

  implicit def optionFutureToValueFutureConverter[T](f: Future[Option[T]]) = new OptionFutureToOptionValueFuture(f)

}
