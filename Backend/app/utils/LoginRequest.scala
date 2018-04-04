package utils

import play.api.i18n.MessagesApi
import play.api.mvc._

/**
  *
  * @project Backend
  * @author sergaben on 04/04/2018.
  *
  */
class LoginRequest[A](request:Request[A],val messagesApi:MessagesApi) extends WrappedRequest(request) with LoginRequestHeader
