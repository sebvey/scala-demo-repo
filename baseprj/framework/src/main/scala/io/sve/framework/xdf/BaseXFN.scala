package io.sve.framework.xdf

import org.apache.spark.sql.types.DataType

class BaseXFN(name: String, dataType: DataType, pathExp: String, nullable: Boolean) extends XFN {

  def $ : XField = XField(
    name = name,
    dataType = dataType,
    pathExp = pathExp,
    nullable = nullable
  )
}

object BaseXFN {
  def from(
    name: String,
    dataType: DataType,
    pathExp: String,
    nullable: Boolean = true
  ) = new BaseXFN(name, dataType, pathExp, nullable)
}
