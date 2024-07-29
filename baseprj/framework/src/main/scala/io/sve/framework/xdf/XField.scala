package io.sve.framework.xdf

import io.sve.framework.xdf.XField.Kind
import org.apache.spark.sql.types.DataType

// instances with all info about the field :
// - name
// - dataType
// - nullable ...
// + pathExp : expression to reach the field, eg. 'image[0].url'

// it's used as attributes of Field navigators
// BaseFN / EnumFN / ArrayFN

class XField[T] private (
  name: String,
  kind: Kind,
  underlying: T, // underlying type, either DataType or case class
  pathExp: String,
  nullable: Boolean
) {
  // TODO - Decode here
  // if kind is
  // - BaseXField -> DataType
  // - ArrayXField -> ArrayType(StructType(Seq(HList(cls).map(_.$.dataType)))
  // - EnumXField -> ArrayType(StructType(Seq(HList(cls).map(_.$.dataType)))

  def dataType: DataType = ???
}

object XField {

  sealed trait Kind
  object BaseXField extends Kind
  object EnumXField extends Kind
  object ArrayXField extends Kind

  def toEnum[T](name: String, underlying: T, pathExp: String, nullable: Boolean = true) =
    new XField[T](name, XField.EnumXField, underlying, pathExp, nullable)

  def toArray[T](name: String, underlying: T, pathExp: String, nullable: Boolean = true) =
    new XField[T](name, XField.ArrayXField, underlying, pathExp, nullable)

  def toBase[T](name: String, underlying: T, pathExp: String, nullable: Boolean = true) =
    new XField[T](name, XField.BaseXField, underlying, pathExp, nullable)
}
