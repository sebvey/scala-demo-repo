package io.sve.framework.xdf

import org.apache.spark.sql.types.{DataType, StructField}

/** Base Field Type, 'alias' of StructField */
case class XBaseField(
  name: String,
  dataType: DataType
)
