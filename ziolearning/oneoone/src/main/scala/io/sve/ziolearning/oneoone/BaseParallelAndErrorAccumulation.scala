package io.sve.ziolearning.oneoone

import zio._
import zio.Console._
import zio.Clock._

import java.io.IOException


object BaseParallelAndErrorAccumulation extends ZIOAppDefault {

  val first = ZIO.succeed(21)
  val second = ZIO.succeed("23")

  // parallel execution of the two effects
  // success is a tuple
  // error of one interrupts the other
  // the returned error is :
  // - the one which fails + Cause.Interrupted for the other
  // - both errors if failed the 'same time'
  val zipPar = first.zipPar(second)

  // race two effects, returning an Either (left for self, right for other)
  // if one succeed, the other is interrupted, success is returned
  // if one fails, the other is still run, waiting for its success
  // if both failed, both errors are returned
  val race = first.raceEither(second)

  // Variants : foreachPar / CollectAllPar, that interrupt all running effects on the first error
  // Variants, with 'Discard' suffix, to Discard returned value(s)

  // PARALLELISM :
  // .withParallelism(N) : set the number of fibers that can be launched concurrently
  // .withParallelismUnbounded : with unlimited fibers

  val collectPar: ZIO[Any, IOException, List[Unit]] =
    ZIO.collectAllPar(
      List.fill(100)(race.flatMap(printLine(_)))
    ).withParallelism(2)


  // ERROR ACCUMULATION INSTEAD OF FAILFAST
  // use case, we want to know all errors for a validation process
  // ZIO.validatePar(Iterator) : ZIO.foreachPar(Iterator)  equivalent but without interruption of other fibers on one failure
  // ZIO.validate(Iterator) : ZIO.foreach(Iterator)
  // ze1 validate ze2 : ze1 zip ze2 equivalent
  // ze1 validatePar ze2 : ze1 zipPar ze2 equivalent

  val toProcess = List("GOOD","BAD", "BAD")
  def zioProcess(s: String): ZIO[Any, String, Int] = if (s == "GOOD") ZIO.succeed(101) else ZIO.fail("VALUE ERROR")
  val validatePar: ZIO[Any, ::[String], List[Int]] = ZIO.validatePar(toProcess)(zioProcess)


  // ZIO.partition / ZIO.partitionPar : foreach / foreachPar equivalent but :
  // - always succeed
  // - returns a sucess type (Iterable[E], Iterable[B])
  // => we can later process the errors and success :-)

  val run: ZIO[Any, Nothing, (Iterable[String], Iterable[Int])] = ZIO.partitionPar(toProcess)(zioProcess).debug

}