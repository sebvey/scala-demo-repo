package io.sve.framework.edf

import org.apache.spark.sql.types.{StructField, StructType}

trait EStruct {
  def structType: StructType = StructType(List.empty[StructField]) // TODO - decode the case object :-)
}
