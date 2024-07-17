package io.sve.ziolearning.oneoone

import zio.Console._
import zio._

import scala.io.StdIn
import java.io.IOException
import scala.util.Try

object BaseZIO extends ZIOAppDefault {

  // EFFECTS ALIASES
  // ZIO[-R,+E,+A]

  // unexceptional : U prefix
  // with environment : R prefix

  // Exceptional Effect
  // IO[E,A]    = ZIO[Any,E,A]
  // RIO[R,A]   = ZIO[R,Throwable,A]
  // Task[A]    = ZIO[Any,Throwable,A]  (mainly to manage legacy code)

  // Unexceptional Effect (U) :
  // UIO[A]     = ZIO[Any,Nothing,A]
  // URIO[R,A]  = ZIO[R,Nothing,A]

  // An effect that can fail with an IOException
  val readLine: ZIO[Any, IOException, String] = Console.readLine

  // SUCCESS / FAILURE
  // An effect that succeeds with a specified value
  val s1: ZIO[Any, Nothing, Int] = ZIO.succeed(42)

  // An effect that fails with given failure (type IO[E,A] = ZIO[Any,E,A])
  val f1: IO[String, Nothing]    = ZIO.fail("Uh oh...")
  val f2: IO[Exception, Nothing] = ZIO.fail(new Exception("Uh oh..."))

  // FROM VALUES

  // ZIO From Option with fromOption
  // -> Success type is the 'optioned' type
  // -> error type is Option[Nothing], which provide no information
  // -> we can map it to something else
  val zOption: IO[Option[Nothing], Int] = ZIO.fromOption(Some(2))
  val zOption2: IO[String, Int]         = zOption.mapError(_ => "Empty Option !!")

  // ZIO from Option with getOrFail
  def parseToInt(s: String): Option[Int] = s.toIntOption

  val r1: IO[Throwable, Int]             = ZIO.getOrFail(parseToInt("1.2"))     // fails with Throwable error type
  val r2: IO[Unit, Int]                  = ZIO.getOrFailUnit(parseToInt("1.2")) // fails with Unit error type
  val r3: IO[NumberFormatException, Int] =
    ZIO.getOrFailWith(new NumberFormatException("Invalid input for Integer"))(parseToInt("1.2"))

  // ZIO from Option with noneOrFail
  val optionalString: Option[String] = Some("Hello")
  val r4: IO[String, Unit]           = ZIO.noneOrFail(optionalString) // fails with content of the optional value
  val r5: IO[Unit, Unit]             = ZIO.noneOrFailWith(optionalString)(e => new NumberFormatException(e))

  // ZIO of optional values :
  val someInt: IO[Nothing, Option[Int]] = ZIO.some(3)
  val noneInt: IO[Nothing, Option[Int]] = ZIO.none

  // ZIO of Either
  // E and A types are the same as contained by Left and Right
  val zEither: IO[String, String]  = ZIO.fromEither(Right("Success"))
  val zEither2: IO[String, String] = ZIO.fromEither(Left("Failure !!"))

  // ZIO of Try
  // (Try always fails with Throwable)
  val zTry: Task[Int]                 = ZIO.fromTry(Try(42 / 0))
  val zTry2: ZIO[Any, Throwable, Int] = ZIO.fromTry(Try(42 / 0)) // aka

  // ZIO of Future
  import scala.concurrent.Future

  // The future must be a def so that the valuation is not triggered when defined
  // (you should test changing def to val)

  def future                            = Future.successful { println("Evaluated"); 42 }
  val zfuture: ZIO[Any, Throwable, Int] = ZIO.fromFuture(implicit ec => future)

  val run = zfuture
    .map(r => if (r == 42) "The Answer !!" else "Mm")
    .flatMap(s => printLine(s"$s"))
    .repeat(Schedule.once)

}
