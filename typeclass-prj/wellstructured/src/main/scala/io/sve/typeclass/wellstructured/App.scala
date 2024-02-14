package io.sve.typeclass.wellstructured

import scala.annotation.tailrec
import org.scalatest.time.Millisecond

object App extends App {

    implicit class ShowableInt(i: Int) {
        def show = println(s"Int: $i")
        def show(desc: String) = println(s"$desc: $i")
    }


    // def map[A,B](ft: Seq[A])(f: A => B): Seq[B] = {

    //     @tailrec
    //     def get(done: Seq[B], todo: Seq[A]): Seq[B] =
    //         if (todo.isEmpty) done
    //         else get(done :+ f(todo.head),todo.tail)

    //     get(Seq.empty[B],ft)

    // }

    // def filter[A](ft: Seq[A])(p: A => Boolean): Seq[A] = {

    //     def get(filtered: Seq[A], toFilter: Seq[A]): Seq[A] =
    //         if (toFilter.isEmpty) filtered
    //         else if (p(toFilter.head)) get(filtered :+ toFilter.head,toFilter.tail)
    //         else get(filtered,toFilter.tail)

    //     get(Seq.empty[A],ft)

    // }

    println("blabla")

    // ETA-EXPANSION : METHOD -> FUNCTION
    // Techno that converts a method to a function type

    def m1(a:Int) = a * 5
    val f1a = m1(_)
    val f1b = m1 _   // other syntax

    def m2(a: Int,b:Int) = a*b
    val f2 = m2(_,_)
    val f2b = m2 _

    // Eta expansion can convert methods that use a obj attribute
    // Resulting Functions are not serializable (needs attribute is scope)
    // Clearly not a good pattern

    var attr: Int = 666 // attribute of singleton App
    def m3(i:Int) = i + attr
    val f3 = m3(_:Int)

    f3(2)  // -> 668
    attr = 2
    f3(2)  // -> 4

    // PARTIALLY APPLIED FUNCTION
    // Application : The process of applying a function to its arguments
    //               in order to produce a return value
    // Partial Application : process of applying a function to some of its
    //                       arguments. A partially-applied function gets
    //                       returned for later use

    // Method to partially applied function
    // -> we create a specific func from a more generic method
    // - We applied some arguments, not all
    // - Unapplied arguments are all in one group
    // - when multiple unapplied args, types have to be precised

    def m4(a:Int, b:Double)(c: Int) = a/b + c.toDouble
    val f4a = m4(1,2)(_)
    val f4b = m4(1,_: Double) (_: Int)
    f4b(3d,2)

    // can be assigned back to a method
    def m5(d: Double, i:Int) = m4(1,_: Double)(_: Int)

    // ? Function -> Partially applied function
    val add = (a: Int) => (b: Int) => a + 2 * b
    val add2 = add(_:Int)(2)

    // CURRYING (from Haskell Brooks Curry)
    // a function that takes multiple arguments
    // can be translated into a series of function calls
    // that each take a single argument

    def f(a: Int, b: Int) = a + b // classic
    def g(a:Int)(b:Int) = f(a,b)  // Curried equivalent


    val gp = {f _}.curried // currying a method
    gp(1)(2).show


}
