package io.sve.framework.xdf

import io.sve.framework.xdf.MyStruct.ImageField
import org.apache.spark.sql.types.{IntegerType, StringType}

case class MyStruct(
  image: ImageField = ImageField(),
  asin: XField[StringType] = XField("asin", StringType)
)

object MyStruct {

  case class ImageField(
    url: XField[StringType] = XField("url", StringType),
    content: XField[IntegerType] = XField("content", IntegerType)
  ) extends XEnumField[ImageField] {
    self =>
    override def __xField: XField[ImageField] = XField("image", self)
  }
}

//TODO NEXT - Voir si on peut simplifier cette syntaxe en passant par l'objet compagnon de XEnumField
// TODO - passer au décodeur
