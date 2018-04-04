package utils

import play.api.mvc.{MessagesRequestHeader, PreferredMessagesProvider}

/**
  *
  * @project Backend
  * @author sergaben on 04/04/2018.
  *
  */
trait LoginRequestHeader extends MessagesRequestHeader with PreferredMessagesProvider
