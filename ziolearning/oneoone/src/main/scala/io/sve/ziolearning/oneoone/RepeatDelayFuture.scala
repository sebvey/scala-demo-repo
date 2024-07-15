package io.sve.ziolearning.oneoone

import zio.Console._
import zio._

import scala.io.{ StdIn, Codec, Source }
import java.io.IOException
import scala.util.Try
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

object RepeatDelayFuture extends ZIOAppDefault {

    def existingFuture(implicit ec: ExecutionContext): Future[Unit] =
        Future(println("Going to the grocery store"))

    val fromFuture = ZIO.fromFuture(implicit ec => existingFuture)

    def delay[R,E,A](zio: ZIO[R,E,A])(duration: Duration): ZIO[R with Clock, E, A] =
        Clock.sleep(duration) zipRight zio

    val hello = print("Hello").zip(printLine("De Lu"))

    val getEnv = System.env("env")
    def printEnv(e: String) = printLine(s"env is {e}")

    val run = fromFuture
        .delay(2.seconds)
        .repeat(Schedule.once)
        .zipLeft(hello)


}
