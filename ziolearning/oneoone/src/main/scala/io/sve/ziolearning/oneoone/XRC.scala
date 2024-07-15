package io.sve.ziolearning.oneoone

import java.io._
import zio.{ZIO, ZIOAppDefault}
import zio.Console._
import scala.io.Source
import scala.concurrent.Future
import zio.Schedule

object XRC extends ZIOAppDefault {

  case class User(name: String)
  case class AuthError(error: String)


  object legacy {
    def login(onSuccess: User => Unit, onFailure: AuthError => Unit): Unit = ???
  }

  val login: ZIO[Any, AuthError, User] =
    ZIO.async[Any, AuthError, User] { callback =>
      legacy.login(
        user => callback(ZIO.succeed(user)),
        err  => callback(ZIO.fail(err))
      )
    }

  val run = ZIO.succeed({})
}
