package io.sve.ziolearning.oneoone

import zio.{ZIO, ZIOAppDefault}

import scala.io.StdIn

object MainThree extends ZIOAppDefault {

  // EFFECT CONSTRUCTOR FOR PURE COMPUTATIONS

  // pure code (no side effects) can benefit from features of ZIO
  // (environment, typed errors, stack safety ...)

  // pure value -> effects
  val ef = ZIO.succeed(StdIn.readLine().toInt)


  def run = ef

}
