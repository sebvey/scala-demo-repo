package io.sve.typeclass.myapp



object App extends App {


  val i: Int = 23
  val s: String = "Hello Type class"

  // ---------------------------------------------------------------------------
  // Basic use of Type Class (useless in real life)
  // (type class Descriptor[T] is defined in other file)

  import Descriptor._
  intDescriptor.getDescription(i)

  // We can now define a polymorphic function
  // It's definition won't change
  // can be used on every type T that has an implicit Descriptor[T] defined


  def describe[A](a: A)(implicit d: Descriptor[A]): Unit = println(d.getDescription(a))


  describe(i)
  describe(s)

  // ---------------------------------------------------------------------------
  // IMPLICIT CLASS
  // We have above a 'pattern' that allows us to have a polymorphic method
  // that can be used on types A that have a corresponding implicit instance
  // of type class Descriptor[A]

  // An other need can be to have a method 'show' directly accessible from an
  // object of type A

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
