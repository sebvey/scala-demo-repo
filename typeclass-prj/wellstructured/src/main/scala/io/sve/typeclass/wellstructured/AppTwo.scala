package io.sve.typeclass.wellstructured

import scala.reflect.ClassTag
import scala.annotation.tailrec
import scala.util.Random
import scala.io.StdIn

import scala.util.chaining._
import scala.language.implicitConversions


object AppTwo extends App {

    val randGen = new Random()

    case class State(
        flipNumber: Int = 0,
        successNumber: Int = 0,
        exitAsked: Boolean = false
    ) {
        def stats: String =
            s"""
            |Stats : $successNumber/$flipNumber (${100*successNumber/flipNumber} %)
            |Bye !!
            """.stripMargin
    }

    def recordExit(state: State): State = {
        println(state.stats)
        state.copy(exitAsked = true)
    }

    def recordSuccess(state: State): State = {
        println(s"Success :-)")
        state.copy(
            successNumber = state.successNumber+1,
            flipNumber = state.flipNumber+1
        )
    }

    def recordFailure(state: State): State = {
        println(s"Failure :-(")
        state.copy(flipNumber = state.flipNumber+1)
    }

    def getInput(): Option[Boolean] = {
        print("Head (h) / Tail (t) / Quit (q) : ")
        StdIn.readLine().toLowerCase() match {
            case "h" => Some(false)
            case "t" => Some(true)
            case "q" => None
            case _ => getInput
        }
    }

    def play(state: State): Unit =
        if (!state.exitAsked) {
            val flipResult = randGen.nextBoolean()
            val input = getInput()
            (input,flipResult) match {
                case (Some(g),r) =>
                    if (g == r) state pipe recordSuccess pipe play
                    else state pipe recordFailure pipe play
                case _ => state pipe recordExit
            }
        }

    play(State())

}
