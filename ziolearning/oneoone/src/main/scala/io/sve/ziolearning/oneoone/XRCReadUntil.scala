package io.sve.ziolearning.oneoone

import java.io._
import zio.{ZIO, ZIOAppDefault}
import zio.Console._
import scala.io.Source
import scala.concurrent.{ExecutionContext, Future}
import zio.Schedule
import zio.{Scope, ZIOAppArgs}


object XRCReadUntil extends ZIOAppDefault {

  def readUntil(acceptInput: String => Boolean): ZIO[Any,IOException,String] =
    readLine.flatMap(s => if (acceptInput(s)) ZIO.succeed(s) else readUntil(acceptInput))

  def isTheAnswer(s: String) = s == "42"
  def run = readUntil(isTheAnswer)

}
