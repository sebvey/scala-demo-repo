package io.sve.ziolearning.oneoone

import java.io._
import zio.{ZIO, ZIOAppDefault}
import zio.Console._
import scala.io.Source
import scala.concurrent.Future
import zio.Schedule

object BaseZIOAsync extends ZIOAppDefault {

def getCache(
  key: Int,
  onSuccess: String => Unit,
  onFailure: Throwable => Unit
  ): Unit = {
    key match {
      case 42 => onSuccess("The Answer")
      case _ => onFailure(new Exception("Don't have the answer"))
    }
  }

def getCacheZIO(key: Int): ZIO[Any,Throwable, String] =
  ZIO.async {
    callback =>
      getCache(
        key,
        s => callback(ZIO.succeed(s)),
        e => callback(ZIO.fail(e)))
  }

val keys = List(42,41,40)

val run = for {
  cached <- ZIO.foreach(keys)(getCacheZIO(_).orElse(ZIO.succeed("Evil")))
  _ <- printLine(s"Cached : $cached")
} yield ()
}
