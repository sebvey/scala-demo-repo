package io.sve.ziolearning.oneoone

import java.io._
import zio.{ZIO, ZIOAppDefault}
import zio.Console._
import scala.io.Source
import scala.concurrent.{ExecutionContext, Future}
import zio.Schedule
import zio.{Scope, ZIOAppArgs}


object XRCRandRecursive extends ZIOAppDefault {

  def target = zio.Random.nextIntBetween(1, 11)

  def recursiveGuess(target: Int): ZIO[Any,IOException, Unit] = for {
    _     <- printLine("Guess :")
    guess <- readLine
    _     <- if (target.toString == guess) printLine("Good Game !")
             else recursiveGuess(target)

  } yield ()

  def run = target.flatMap(recursiveGuess)

}
