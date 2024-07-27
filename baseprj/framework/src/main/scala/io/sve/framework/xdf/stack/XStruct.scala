package io.sve.framework.xdf.stack

import org.apache.spark.sql.types.{StructField, StructType}

trait XStruct {
  def structType: StructType = StructType(List.empty[StructField]) // TODO - decode the case object :-)
}
