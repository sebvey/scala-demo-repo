package io.sve.baseprj.sandbox

import shapeless.{::, Generic, HList, HNil}


object MainThree extends App {

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

    // Generic Product Encoder
    implicit def genericEncoder[A,R](
      implicit
      gen: Generic.Aux[A,R],
      enc: CsvEncoder[R]
    ): CsvEncoder[A] = instance( a => enc.encode(gen.to(a)))
  }

  def writeCsv[A](vs: List[A])(implicit enc: CsvEncoder[A]): String =
    vs.map(value => enc.encode(value).mkString(",")).mkString("\n")


  val ex = List(
    Ex("YeLLo", 23),
    Ex("Hi", 34)
  )

  println(writeCsv(ex))

}
