package io.sve.baseprj.sandbox

import shapeless.{::, Generic, HList, HNil}

object MainTwo extends App {

  case class Ex(name: String, number: Int)

  trait CsvEncoder[A] {
    def encode(value: A): List[String]
  }

  object CsvEncoder {
    // 'summoner' method
    def apply[A](implicit enc: CsvEncoder[A]): CsvEncoder[A] = enc

    // 'constructor' method
    def instance[A](func: A => List[String]): CsvEncoder[A] =
      new CsvEncoder[A] {
        def encode(value: A): List[String] = func(value)
      }

    // basic types encoders
    implicit val stringEncoder: CsvEncoder[String] =
      CsvEncoder.instance(s => List(s))

    implicit val intEncoder: CsvEncoder[Int] =
      CsvEncoder.instance(i => List(i.toString))

    // HList encoders
    implicit val hnilEncoder: CsvEncoder[HNil] = instance(hnil => Nil)

    implicit def hlistEncoder[H, T <: HList](implicit
      hEncoder: CsvEncoder[H],
      tEncoder: CsvEncoder[T]
    ): CsvEncoder[H :: T] =
      instance { case h :: t => hEncoder.encode(h) ++ tEncoder.encode(t) }

    // Concrete Product Encoder
    implicit val exEncoder: CsvEncoder[Ex] = {
      val gen = Generic[Ex]
      val enc = CsvEncoder[gen.Repr]
      instance( ex => enc.encode(gen.to(ex)))

    }
  }

  def writeCsv[A](vs: List[A])(implicit enc: CsvEncoder[A]): String =
    vs.map(value => enc.encode(value).mkString(",")).mkString("\n")


  val ex = List(
    Ex("Hiillo", 23),
    Ex("Hi", 34)
  )

  println(writeCsv(ex))

}
