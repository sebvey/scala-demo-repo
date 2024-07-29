package io.sve.framework.xdf

import io.sve.framework.xdf.DFNavigator.{ImageXFN, InfoXFN}
import org.apache.spark.sql.types.{IntegerType, StringType}

case class DFNavigator(
  info: InfoXFN = InfoXFN(),
  image: ImageXFN = new ImageXFN(),
  asin: BaseXFN = BaseXFN.from("asin", StringType, "path_exp_TODO")
)

object DFNavigator {
  val fakePath: String       = "TODO"

  case class InfoNav(
    id: BaseXFN = BaseXFN.from("id", IntegerType, fakePath),
    name: BaseXFN = BaseXFN.from("name", StringType, fakePath)
  )
  case class InfoXFN(
    $ : XField[InfoNav] = XField.toEnum("info", InfoNav(), fakePath)
  ) extends InfoNav with EnumXFN[InfoNav]

  class ImageElNav(i: Int) {
    val url: BaseXFN     = BaseXFN.from("url", StringType, fakePath + i.toString)
    val content: BaseXFN = BaseXFN.from("content", IntegerType, fakePath + i.toString)
  }
  class ImageElXFN(i: Int) extends ImageElNav(i) with EnumXFN[ImageElNav] {
    override def $ : XField[ImageElNav] = XField.toEnum(s"image[$i]", new ImageElNav(i), fakePath)
  }
  class ImageXFN extends ArrayXFN[ImageElNav] {
    val $ : XField[ImageElNav]    = XField.toArray("image", new ImageElNav(0), fakePath)
    def apply(i: Int): ImageElXFN = new ImageElXFN(i)
  }
}
