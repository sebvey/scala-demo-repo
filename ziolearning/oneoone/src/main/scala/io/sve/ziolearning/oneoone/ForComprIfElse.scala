package io.sve.ziolearning.oneoone

import java.io._
import zio.{ZIO, ZIOAppDefault}
import zio.Console._
import scala.io.Source

object ForComprIfElse extends ZIOAppDefault {

  val random                  = ZIO.attempt(scala.util.Random.nextInt(3) + 1)
  def printLine(line: String) = ZIO.attempt(println(line))
  val readLine                = ZIO.attempt(scala.io.StdIn.readLine())

  val fcZIO = for {
    _     <- printLine("Guess a number from 1 to 3:")
    num   <- random
    guess <- readLine
    _     <- if (guess == num.toString) printLine("You guessed right!")
             else printLine(s"You guessed wrong, the number was $num!")

  } yield ()

  def run = fcZIO

}
