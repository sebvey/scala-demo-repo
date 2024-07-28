package io.sve.framework.xdf

import io.sve.framework.xdf.DFNavigator.{ImageElNav, ImageElXFN, ImageXFN, InfoXFN}
import org.apache.spark.sql.types.{DataType, IntegerType, StringType}

case class DFNavigator(
  info: InfoXFN = InfoXFN(),
  image: ImageXFN = new ImageXFN(),
  tmp: ImageElXFN = new ImageElXFN(0), // TODO TMP
  asin: BaseXFN = BaseXFN.from("asin", StringType, "path_exp_TODO")
)

object DFNavigator {

  val fakeDataType: DataType = StringType
  val fakePath: String       = "fakePath"

  case class InfoNav(
    id: BaseXFN = BaseXFN.from("id", IntegerType, "path_exp_TODO"),
    name: BaseXFN = BaseXFN.from("name", StringType, "path_exp_TODO")
  )
  case class InfoXFN($ : XField = XField("info", fakeDataType, fakePath)) extends InfoNav with EnumXFN[InfoNav]

  class ImageElNav(i: Int) {
    val url: BaseXFN = BaseXFN.from("url", StringType, "path_exp_TODO" + i.toString)
    val content: BaseXFN = BaseXFN.from("content", IntegerType, "path_exp_TODO" + i.toString)
  }
  class ImageElXFN(i: Int) extends ImageElNav(i) with EnumXFN[ImageElNav] {
    override def $: XField = XField(s"image[$i]", fakeDataType, fakePath)
  }

  class ImageXFN extends ArrayXFN[ImageElNav] {
    val $ : XField = XField("image", fakeDataType, fakePath)
    def apply(i: Int): ImageElXFN = new ImageElXFN(i)
  }
}
