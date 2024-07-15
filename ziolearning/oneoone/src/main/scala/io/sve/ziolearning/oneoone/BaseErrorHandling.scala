package io.sve.ziolearning.oneoone

import zio._

import scala.io.StdIn
import java.io.IOException
import java.io.FileNotFoundException

// cf doc : https://zio.dev/overview/handling-errors/

object BaseErrorHandling extends ZIOAppDefault {

  val readInt = Console.readLine.flatMap(s => ZIO.attempt(s.toInt))

  // New effect on failure
  val toSuccess = for {
    _ <- Console.printLine("ENTER AN INTEGER")
    r <- readInt.orElse(ZIO.succeed(42))
    _ <- Console.printLine(s"result : $r")
  } yield ()

  // ZIO[R,E,A] to ZIO[R,Nothing,Either[E,A]
  val toEither = for {
    _ <- Console.printLine("ENTER AN INTEGER")
    r <- readInt.either
    _ <- Console.printLine(s"result : $r")
  } yield ()


  // Catch All

  def openFile(path: String): ZIO[Any,IOException,Array[Byte]] = ???

  val z: ZIO[Any, IOException, Array[Byte]] =
    openFile("primary.json").catchAll { error =>
      for {
        _    <- ZIO.logErrorCause("Could not open primary file", Cause.fail(error))
        file <- openFile("backup.json")
      } yield file
    }

  // Catch Some

  val data: ZIO[Any, IOException, Array[Byte]] =
    openFile("primary.data").catchSome {
      case _ : FileNotFoundException =>
        openFile("backup.data")
    }

  val run = toSuccess
}
