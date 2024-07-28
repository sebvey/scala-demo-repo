package io.sve.framework.xdf

import org.apache.spark.sql.types.DataType

class EnumXFN[T](
  name: String,
  dataType: DataType,
  pathExp: String,
  nullable: Boolean
) extends T with XFN {
  self: T =>
  def $: XField = XField(name, dataType, pathExp, nullable)
}

object EnumXFN {
  def from[T](name: String, navigator: T, pathExp: String, nullable: Boolean = true): EnumXFN[T] = ???
}