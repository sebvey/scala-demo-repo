package io.sve.baseprj.sandbox

import org.apache.spark.sql.types.{DataType, IntegerType, StringType, StructField, StructType}
import shapeless.{::, Generic, HList, HNil}


object MainFour extends App {

  case class Field(
                   name: String,
                   dataType: DataType,
                   nullable: Boolean,
                   desc: String
                  )

  trait StructFieldEncoder[A] {
    def encode(value: A): List[StructField]
  }

  object StructFieldEncoder {
    def apply[A](implicit enc: StructFieldEncoder[A]): StructFieldEncoder[A] = enc
    def instance[A](func: A => List[StructField]): StructFieldEncoder[A] = (value: A) => func(value)

    // basic types encoders
    implicit val structFieldEncoder: StructFieldEncoder[StructField] =
      StructFieldEncoder.instance(sf => List(sf))

    implicit val fieldEncoder: StructFieldEncoder[Field] =
      StructFieldEncoder.instance( f => List(StructField(f.name,f.dataType,f.nullable)) )

    // HList encoders
    implicit val hnilEncoder: StructFieldEncoder[HNil] = instance(hnil => Nil)

    implicit def hlistEncoder[H, T <: HList](
      implicit
        hEncoder: StructFieldEncoder[H],
        tEncoder: StructFieldEncoder[T]
    ): StructFieldEncoder[H :: T] = instance { case h :: t => hEncoder.encode(h) ++ tEncoder.encode(t) }

    // Generic Product Encoder
    implicit def genericEncoder[A,R](
      implicit
      gen: Generic.Aux[A,R],
      enc: StructFieldEncoder[R]
    ): StructFieldEncoder[A] = instance(a => enc.encode(gen.to(a)))
  }

  def getStructType[A](schema: A)(implicit enc: StructFieldEncoder[A]): StructType = StructType(enc.encode(schema))


  // FIRST POSSIBLE USAGE

  case class MySchema(
    firstname: StructField = StructField("firstname", StringType),
    number: StructField = StructField("number", IntegerType)
  )

  object MySchema {
    val fields: MySchema = MySchema()
    val structType: StructType = getStructType(fields)
  }

  println("FIRST USAGE")
  println(s"${MySchema.fields.firstname}")
  MySchema.structType.foreach(sf => println(s" - $sf"))


  // ANOTHER POSSIBLE USAGE

  case class MonSchema(
    prenom: Field = Field("prénom", StringType, nullable = true, "ce qui est avant le nom"),
    age: Field = Field("age", IntegerType, nullable = true, "ce qui quantifie l'inquantifiable"),
  )

  object MonSchema {
    val fields: MonSchema = MonSchema()
    val structType: StructType = getStructType(fields)
  }

  println("SECOND USAGE")
  println(s"${MonSchema.fields.prenom}")
  MonSchema.structType.foreach(sf => println(s" - $sf"))

}
