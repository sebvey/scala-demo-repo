package io.sve.framework.xdf

import io.sve.framework.xdf.DFNavigator.ImageXN
import org.apache.spark.sql.types.{IntegerType, StringType}

case class DFNavigator(
  image: EnumXFN[ImageXN.type] = EnumXFN.from("image", ImageXN(), "path_exp_TODO"),
  asin: BaseXFN = BaseXFN.from("asin", StringType, "path_exp_TODO")
)

object DFNavigator {

  case class ImageXN(
    url: BaseXFN = BaseXFN.from("url", StringType, "path_exp_TODO"),
    content: BaseXFN = BaseXFN.from("content", IntegerType, "path_exp_TODO")
  )
}
