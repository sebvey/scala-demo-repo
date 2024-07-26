package io.sve.ziolearning.zstream

import zio.Console.printLine
import zio.stream.{ZPipeline, ZSink, ZStream}
import zio._

object SB extends ZIOAppDefault {

  // Basically
  // ZStream : source of streamed elements (0 to infinite number) / Elements are made available over time
  // ZPipeline : transformation : ZStream => ZStream
  // ZSink : consumption of the entire stream

  // Many ways to define transformations
  // - we can define new ZStream from other ZStream
  // - we can transformation in ZPipelines, chain them


  // ZSTREAM

  val aStream = ZStream(1,2,3,4)   // stream with finite available elements
  val bStream = ZStream(1,2,3).repeat(Schedule.spaced(1.second))   // stream that produce elements every second

  // a stream can be a transformation of an other one
  val cStream = aStream.map(_.toString)
  val dStream = aStream.mapZIO(a => ZIO.succeed(2*a))


  // STREAM CONSUMPTION

  // pass each element to a callback : foreach
  val toCallbackZIO = dStream.foreach(printLine(_))

  // plug to a sink : destination that consumes elements
  // ZSink[-R, +E, -In, +L, +Z] :
  // - In : elements input type
  // - L : leftover elements type (elements not consumed)
  // - Z : output type

  val basicStream = ZStream(1,3,5,7)
  val basicSink = ZSink.sum[Int].mapZIO(printLine(_))
  val toSinkZIO = basicStream.run(basicSink)
  // def run = toSinkZIO


  // SINKS - RECEPTACLE FOR VALUES. THEY CONSUME VALUES

  // SINKS CREATION
  // Sink that sums all elements (-> for finite streams only)
  val sumSink: ZSink[Any, Nothing, Int, Nothing, Int] = ZSink.sum[Int]

  // Sink that :
  // - chunks the 5 first elements (and leftover the others)
  // - map each element of the chunk to string
  val baseSink: ZSink[Any, Nothing, Int, Int, Chunk[Int]] = ZSink.take[Int](5) // ZSink.take(5) when inference works

  // SINKS 'REFINEMENT'
  // (the more we do in ZPipeline, the better, but we can also define transformations at ZSink level)

  // map output (.map() / .mapZIO)
  val moSink = baseSink.map(chunk => chunk.map(_.toString))

  // map input (.contramap() / .contramapZIO())
  val cmiSink: ZSink[Any, Nothing, String, Int, Chunk[Int]] = baseSink.contramap[String](_.toInt)

  // fyi map both can be done at once with .dimap()

  // ZSINK AND LEFTOVERS
  // Sink constructed from another one, adding leftovers collection
  // -> no more leftovers
  // -> they are in the returned Tuple as a chunk (OutputType, Chunk[ElementType])
  val sink02: ZSink[Any, Nothing, Int, Nothing, (Chunk[Int], Chunk[Int])] = baseSink.collectLeftover

  // idem, adding leftovers ignoring
  val sink03: ZSink[Any, Nothing, Int, Nothing, Chunk[Int]] = baseSink.ignoreLeftover


  // ZPIPELINE - Transformation on ZStream (called ZTRANSDUCER IN
  // ZPipeline[-Env, +Err, -In, +Out]

  val pxStream = ZStream("1","2","3","4","5")

  val pipe1 = ZPipeline.map[String, Int](_.toInt)
  val pipe2: ZPipeline[Any, Nothing, Int, Int] =  ZPipeline.filter[Int](e => (e % 2) != 0).map(_ * 2)
  val mainPipe = pipe1 >>> pipe2

  val pxSink = ZSink.sum[Int].mapZIO(printLine(_))

  val pxZIO = pxStream.via(mainPipe).run(pxSink)

  val run = pxZIO



  // TODO - ZIO Streams, Part 1 : Essential Stream Components 12:00
  // https://www.youtube.com/watch?v=bp3nM6bfzJk&list=PLmtsMNDRU0Bzu7NfhTiGK7iCYjcFAYlal&index=5


}
