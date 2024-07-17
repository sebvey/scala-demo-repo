package io.sve.ziolearning.zstream

import zio.Console.printLine
import zio.stream.ZStream
import zio._

import scala.language.postfixOps


object ZStreamBasics extends ZIOAppDefault {

//  // BASIC CONSTRUCTORS
//
//  val _ = ZStream.fromIterable(1 to 10)
//  val _ = ZStream(1,2,4,8)
//  val _ = ZStream.range(1,5)
//  val _ = ZStream.fromChunk(Chunk(1, 2, 3))
//
//  val _ = ZStream.succeed(42)
//  val _ = ZStream.fail(666)
//
//  val _= ZStream.fromZIO(ZIO.succeed(42)) ++ ZStream.fromZIO(ZIO.succeed(24)) // concat
//
//  // REPETITION CONSTRUCTORS
//
//  val _ = ZStream.repeat(1) // repeat '1' indefinitely
//  val _ = ZStream.repeatWithSchedule(42, Schedule.spaced(1.seconds))
//  val _ = ZStream(1,2).repeat(Schedule.forever) // repeat the existing stream on a provided schedule (here forever)
//  val _ = ZStream.iterate(1)(i => i + i * 2)
//
//  // YET OBSCUR ONES
//  // - ZStream.service[R]
//  // - ZStream.scoped

  val run = ZStream(1,2,3).repeat(Schedule.spaced(1.second)).foreach(printLine(_))

  // ZStream Running Strategies
  //  • Run the stream to completion, discarding its result and returning the Unit value
  //  • Run the stream to completion, collecting all of its values into a Chunk
  //  • Run the stream to return the first value without running the rest of the stream
  //  • Run the stream to completion and return the last value
  //  • Fold over the stream values to produce some summary value, consuming only as many values as are necessary to compute the summary



}
