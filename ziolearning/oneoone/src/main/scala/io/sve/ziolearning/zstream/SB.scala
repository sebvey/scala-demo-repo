package io.sve.ziolearning.zstream

import zio.Console.printLine
import zio.stream.{ZSink, ZStream}
import zio._

object SB extends ZIOAppDefault {

  // ZStream = source of streamed elements (0 to infinite number)
  // Elements are made available over time


  val aStream = ZStream(1,2,3,4)   // stream with finite available elements
  val bStream = ZStream(1,2,3).repeat(Schedule.spaced(1.second))   // stream that produce elements every second

  // a stream can be a transformation of an other one
  val cStream = aStream.map(_.toString)
  val dStream = aStream.mapZIO(a => ZIO.succeed(2*a))


  // STREAM CONSUMPTION

  // pass each element to a callback : foreach
  val toCallback = dStream.foreach(printLine(_))

  // plug to a sink : destination that consumes elements
  // ZSink[-R, +E, -In, +L, +Z] :
  // - In : elements input type
  // - L : leftover elements type (elements not consumed)
  // - Z : output type

  // Sink that sums all elements (-> for finite streams only)
  val sumSink: ZSink[Any, Nothing, Int, Nothing, Int] = ZSink.sum[Int]

  // Sink that :
  // - chunks the 5 first elements (and leftover the others)
  // - map each element of the chunk to string
  val take5toString: ZSink[Any, Nothing, Int, Int, Chunk[String]] =
    ZSink.take(5)
      .map(chunk => chunk.map(_.toString))

  // TODO - ZIO Streams, Part 1 : Essential Stream Components 12:00


 def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] = ???
}
