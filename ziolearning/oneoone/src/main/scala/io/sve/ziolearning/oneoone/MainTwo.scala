package io.sve.ziolearning.oneoone

import zio._

import scala.io.StdIn

object MainTwo extends ZIOAppDefault {

  //////
  //
  // SEQUENTIAL OPERATORS SANDBOX
  //
  //////

  // ATTEMPT : creates a Task type from code
  // Task[A] = ZIO[Any,Throwable,A]

  val firstName: Task[String] = ZIO.attempt(StdIn.readLine("First name : "))
  val lastName:  Task[String] = ZIO.attempt(StdIn.readLine("Last name : "))

  def printLine(s: Any): ZIO[Any,Throwable,Unit] = ZIO.attempt(println(s"$s"))

  def fail(s: Any): ZIO[Any,String,Unit] = ZIO.fail(s"$s ??? FAILED !!!")


  // ZIP METHODS :
  // sequence of effects
  // no dependency between first effect and second one (as opposed to flatMap)

  // zipWith : result between the two effects is given by a provided function
  val fullNameAsString: Task[String] = firstName.zipWith(lastName)((first,last) => s"$first $last")

  // zip : result of the two effects is a tuple
  val fullNameAsTuple: Task[(String, String)] = firstName.zip(lastName)

  // zipLeft (<*) / zipRight (*>) : result is respectly from left effect / right effect
  // -> used when result of effect is not needed, eg. printLine
  val strAge: ZIO[Any, Throwable, String] = ZIO.attempt(print("Age please : ")) zipRight ZIO.attempt(StdIn.readLine)
  val strGender: ZIO[Any, Throwable, String] = ZIO.attempt(print("Gender, please : ")) *> ZIO.attempt(StdIn.readLine)


  // FLATMAP :
  // chain effects
  // (no matter if not the same Error types, an higher type will be output ...)
  val askNFail: ZIO[Any, Serializable, Unit] = fullNameAsTuple.flatMap(s => fail(s))
  val askNPrint: ZIO[Any, Throwable, Unit] = fullNameAsTuple.flatMap(s => printLine(s))


  // FOREACH :
  // a single effect from an iteration over an effect
  val printNumbers = ZIO.foreach((1 to 10).toList){ n => printLine(n) }

  // COLLECT ALL :
  // a single effect from a list of effects
  val getFour: Task[Int] = ZIO.attempt{ println(4) ; 4 }
  val getFours: Task[List[Int]] = ZIO.collectAll(List(getFour, getFour))

  val run = getFours

}
