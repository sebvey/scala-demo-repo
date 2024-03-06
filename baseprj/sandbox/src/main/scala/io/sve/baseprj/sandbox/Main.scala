package io.sve.baseprj.sandbox

object Main extends App {

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

    // encoders
    implicit val exCsvEncoder: CsvEncoder[Ex] =
      CsvEncoder.instance(e => List(e.name, e.number.toString))
  }

  def writeCsv[A](vs: List[A])(implicit enc: CsvEncoder[A]): String =
    vs.map(value => enc.encode(value).mkString(",")).mkString("\n")

  val ex = List(
    Ex("hello", 23),
    Ex("Hi", 34)
  )

  println(writeCsv(ex))

}
