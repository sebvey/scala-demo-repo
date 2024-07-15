package io.sve.ziolearning.oneoone

import java.io._
import zio.{ZIO, ZIOAppDefault}
import zio.Console._
import scala.io.Source

object ArgsPrinter extends ZIOAppDefault {

  def run = for {
    _    <- printLine("HEY !!")
    args <- getArgs
    _    <- ZIO.foreach(args)(arg => printLine(s"arg : $arg"))
  } yield ()
}
