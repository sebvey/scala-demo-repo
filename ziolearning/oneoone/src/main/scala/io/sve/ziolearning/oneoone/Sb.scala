package io.sve.ziolearning.oneoone

import zio.Console._
import zio._

import scala.io.{ StdIn, Codec, Source }
import java.io.IOException
import scala.util.Try

object Sb extends ZIOAppDefault {

    def stepOne(in: Option[Int]) = ZIO.fromOption(in).mapError(_ => "Got Nothing")
    def stepTwo(one: Int) = ZIO.attempt(10 / one)
    def stepTwoBis(one: Int) = one / 10
    def toStdOut(v: Any) = zio.Console.printLine(v)

    val in: Option[Int] = None
    val run = stepOne(in)
        .map(stepTwoBis)
        .mapError()
        .flatMap(toStdOut)

    // val simpleURL = "https://phet-dev.colorado.edu/html/build-an-atom/0.0.0-3/simple-text-only-test-page.html"

    // def safeDownload(url: String) =
    //     ZIO.attemptBlocking {
    //         Source.fromURL(url)(Codec.UTF8).mkString
    //     }

    // def printURL(url: String) = printLine(url)

    // val run = safeDownload(simpleURL)
    //     .flatMap(printURL)

}
