package io.sve.framework.xdf

import io.sve.framework.xdf.DFNavigator.{ImageXFN, InfoXFN}
import org.apache.spark.sql.types.{DataType, IntegerType, StringType}

case class DFNavigator(
  info: InfoXFN = InfoXFN(),
  image: ImageXFN = ImageXFN(),
  asin: BaseXFN = BaseXFN.from("asin", StringType, "path_exp_TODO")
)

object DFNavigator {

  val fakeDataType: DataType = StringType
  val fakePath: String       = "fakePath"

  case class InfoNav(
    id: BaseXFN = BaseXFN.from("id", IntegerType, "path_exp_TODO"),
    name: BaseXFN = BaseXFN.from("name", StringType, "path_exp_TODO")
  )
  case class InfoXFN($ : XField = XField("info", fakeDataType, fakePath)) extends InfoNav with EnumXFN

  case class Image(
    url: BaseXFN = BaseXFN.from("url", StringType, "path_exp_TODO"),
    content: BaseXFN = BaseXFN.from("content", IntegerType, "path_exp_TODO")
  )
  case class ImageXFN(
    $ : XField = XField("image", fakeDataType, fakePath),
    nav: Image = Image()
  ) extends ArrayXFN[Image]
}
