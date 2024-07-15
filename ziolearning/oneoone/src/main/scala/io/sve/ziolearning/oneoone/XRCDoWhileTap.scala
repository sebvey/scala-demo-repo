package io.sve.ziolearning.oneoone

import java.io._
import zio.{ZIO, ZIOAppDefault}
import zio.Console._
import scala.io.Source
import scala.concurrent.{ExecutionContext, Future}
import zio.Schedule
import zio.{Scope, ZIOAppArgs}


object XRCDoWhileTap extends ZIOAppDefault {

  def doWhile[R,E,A](body: ZIO[R,E,A])(condition: A => Boolean): ZIO[R,E,A] =
    body.flatMap( r => if (condition(r)) ZIO.succeed(r) else doWhile(body)(condition))

  def rnd = zio.Random.nextIntBetween(40,50).tap(i => printLine(s"Generated : $i"))

  def condition(i: Int) = i == 42

  def run = doWhile(rnd)(condition)
}
