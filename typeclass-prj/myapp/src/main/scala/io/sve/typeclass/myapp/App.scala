package io.sve.typeclass.myapp



object App extends App {


  val i: Int = 23
  val s: String = "Hello Type class"

  // ---------------------------------------------------------------------------
  // Type Class :
  // - a contract for types
  // - instances of types

  trait Descriptor[A] {
    def getDescription(a:A): String
  }

  object Descriptor {

      implicit val intDescriptor = new Descriptor[Int] {
          override def getDescription(a: Int): String = s"Int: $a"
      }

      implicit val stringDescriptor = new Descriptor[String] {
          def getDescription(s: String): String = s"String: $s"
      }
  }

  // basic use (useless in real life)
  import Descriptor._
  intDescriptor.getDescription(i)

  // We can now define a polymorphic function
  // It's definition won't change
  // can be used on every type T that has an implicit Descriptor[T] defined


  def describe[A](a: A)(implicit d: Descriptor[A]): Unit =
    println(d.getDescription(a))

  describe(i)
  describe(s)

  // ---------------------------------------------------------------------------
  // IMPLICIT CLASS
  // We have above a 'pattern' that allows us to have a polymorphic method
  // that can be used on types A that have a corresponding implicit instance
  // of type class Descriptor[A]

  // An other need can be to have a method 'describe' directly accessible from
  // an object of type A

  // The implicit class bellow allows us to implicitly convert a type A to a
  // Descriptible[A], with a method describe
  // that uses the type class Descriptor

  // // NOT GENERIC VERSION
  // implicit class Descriptible(i: Int) {
  //   def describe(implicit d: Descriptor[Int]): Unit = println(d.getDescription(i))
  // }

  // i.describe

  // GENERIC VERSION
  implicit class Descriptible[A](a: A) {
    def describe(implicit d: Descriptor[A]): Unit = println(d.getDescription(a))
  }

  i.describe
  s.describe

  // Extending AnyVal ... Why ??



}
