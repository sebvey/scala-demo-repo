package io.sve.ziolearning.oneoone

import zio.Console._
import zio._

import scala.io.{StdIn, Codec, Source}
import java.io.IOException
import scala.util.Try
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

object RecursiveEffects extends ZIOAppDefault {

  // ZIO effects are stack-safe for arbitrarily recursive effects.
  // So we can write ZIO functions that call themselves to implement any
  // kind of recursive logic with ZIO

  val readInt = for {
    line <- readLine
    int <- ZIO.attempt(line.toInt)
  } yield int

  val readIntOrRetry: ZIO[Any, IOException, Int] =
    readInt
      .orElse(
        printLine("Please enter a VALID integer :")
          .zipRight(readIntOrRetry)
      )

  val run = for {
    _ <- printLine("Enter an integer :")
    i <- readIntOrRetry
    _ <- printLine(s"Int : ${i}")
  } yield ()
}
