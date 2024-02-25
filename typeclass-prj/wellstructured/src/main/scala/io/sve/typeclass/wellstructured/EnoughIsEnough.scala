package io.sve.typeclass.wellstructured

import scala.math.pow

import TestSuite._
import scala.collection.mutable.ListBuffer

object EnoughIsEnough extends App {

  def latestClock(a: Int, b: Int, c: Int, d: Int): String = {
    val all = ListBuffer(a,b,c,d)
    val first = all.filter(_ < 3).max
    all -= first
    val sec = all.filter(_ < 4).max
    all -= sec
    val third = all.filter(_ < 6).max
    all -= third
    val forth = all(0)

    s"${(10*first+sec).toString}:${(10*third+forth).toString}"
  }

  latestClock(3,1,4,8).show

}
