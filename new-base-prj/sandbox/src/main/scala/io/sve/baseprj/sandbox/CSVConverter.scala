package io.sve.baseprj.sandbox

import scala.util.Try
import com.fasterxml.jackson.module.scala.deser.overrides
import scala.util.Success


trait CSVConverter[T] {
    def from(s: String): Try[T]
    def to(t: T): String
}

object CSVConverter {
    def apply[T](implicit cc: CSVConverter[T]): CSVConverter[T] = cc

    // Primitives

    implicit def stringFieldConverter: CSVConverter[String] = new CSVConverter[String] {
        def from(s: String): Try[String] = Success(s)
        def to(t: String): String = t
    }

    implicit def intFieldConverter: CSVConverter[Int] = new CSVConverter[Int] {
        def from(s: String): Try[Int] = Try(s.toInt)
        def to(t: Int): String = t.toString
    }

    def fieldsListConverter[A]
}
