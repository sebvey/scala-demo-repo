package io.sve.typeclass.wellstructured

object TestSuite {

      case class Test[I,R](input: I, expected: R) {

        def runOn(f: I => R): Boolean = {
            print(s"Input : ${input} / Expected: ${expected} => ")
            if(f(input: I) == expected) {
                println("🟢\n")
                true
            }
            else {
                println("🔴\n")
                false}
        }
    }


    implicit class TestsOp[I,R](tests: Seq[Test[I,R]]) {

        def runOn(f: I => R) =
            if (tests.map(_.runOn(f)).min) println("=> 🟢🟢 ALL GOOD 🟢🟢")
            else println("=> 🔴🔴 SOME TESTS FAILED 🔴🔴")

    }

    implicit class Showable[T](t:T) {
        def show = println(t)
    }


    def timeIt[T](reps: Int) (code: => T): Double = {

        List.fill(reps) {
            val startTime = System.nanoTime
            code
            val stopTime = System.nanoTime
            val delta = stopTime - startTime
            delta/1000000d
        }.sum/reps
    }
}
