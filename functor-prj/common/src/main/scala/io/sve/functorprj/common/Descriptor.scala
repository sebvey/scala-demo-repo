package io.sve.functorprj.common


// Descriptor is a type class
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
