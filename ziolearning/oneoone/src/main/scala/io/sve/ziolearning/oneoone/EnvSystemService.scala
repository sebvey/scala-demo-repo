package io.sve.ziolearning.oneoone

import zio.Console._
import zio._

import scala.io.{StdIn, Codec, Source}
import java.io.IOException
import scala.util.Try
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

object EnvSystemService extends ZIOAppDefault {

  // TODO a getOrFailWith should be better
  def getOrUndefine(o: Option[String]): String = o match {
    case None    => "UNDEFINED"
    case Some(e) => e
  }

  // Using ZIO System Service
  // System.env() / System.property()

  // Using ZIO Console Service
  // Console.printLine() / Console.readLine()

  val getEnv = System.env("ENV").map(getOrUndefine)
  def printEnv(e: String) = printLine(s"env variable is ${e}")

  val getProperty = System.property("myproperty").map(getOrUndefine)
  def printProp(p: String) = printLine(s"property is ${p}")

  // ZIO Random Service exposes essentially the scala.util.Random interface
  // but returning functional effects

  val getRandInt = Random.nextInt

  val run = for {
    e <- getEnv
    _ <- printEnv(e)

    p <- getProperty
    _ <- printProp(p)

    i <- getRandInt
    _ <- printLine(s"Generated Int : ${i}")

  } yield ()
}

// ENV vs PROPERTY
// Properties are contained only within the Java platform,
// while Environment Variables are global at the Operating System level,
// available to all applications running on the same machine
// jva arg to pass a property : -DpropertyName=value
