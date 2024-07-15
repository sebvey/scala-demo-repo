package io.sve.ziolearning.oneoone

import zio.Console._
import zio._

import scala.io.{StdIn, Codec, Source}
import java.io.IOException
import scala.util.Try
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

object EnvSystemService extends ZIOAppDefault {

  // ENV vs PROPERTY
  // Properties are contained only within the Java platform,
  // while Environment Variables are global at the Operating System level,
  // available to all applications running on the same machine
  // jva arg to pass a property : -DpropertyName=value

  def getOrUndefined(o: Option[String]): String = o match {
    case None    => "UNDEFINED"
    case Some(e) => e
  }

  val getEnv = System.env("ENV").map(getOrUndefined)
  val getProperty = System.property("myproperty").map(getOrUndefined)

  def printEnv(e: String) = printLine(s"env variable is ${e}")
  def printProp(p: String) = printLine(s"property is ${p}")

  val run = for {
    e <- getEnv
    _ <- printEnv(e)

    p <- getProperty
    _ <- printProp(p)

  } yield ()
}
