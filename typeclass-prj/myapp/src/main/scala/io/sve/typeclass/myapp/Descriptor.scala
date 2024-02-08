package io.sve.typeclass.myapp


// Descriptor is a type class :
// trait is the contract
// implicits are the implementation of types

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
