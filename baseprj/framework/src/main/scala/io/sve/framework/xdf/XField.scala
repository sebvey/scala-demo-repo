package io.sve.framework.xdf

import org.apache.spark.sql.types.DataType

// instances with all info about the field :
// - name
// - dataType
// - nullable ...
// + pathExp : expression to reach the field, eg. 'image[0].url'

// it's used as attributes of Field navigators
// BaseFN / EnumFN / ArrayFN

case class XField(
  name: String,
  dataType: DataType,
  pathExp: String,
  nullable: Boolean = true
)
