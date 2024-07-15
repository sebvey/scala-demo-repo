package io.sve.ziolearning.oneoone

import zio._

import scala.io.StdIn

object ErrorHandling extends ZIOAppDefault {

  val readInt = for {
    line <- Console.readLine
    int <- ZIO.attempt(line.toInt)
  } yield int

  val run = for {

    _ <- Console.printLine("ENTER AN INTEGER")
    i <- readInt.orElse(ZIO.succeed(42))
    _ <- Console.printLine(s"Int : ${i}")
  } yield ()
}
