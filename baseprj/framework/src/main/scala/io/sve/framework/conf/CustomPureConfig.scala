package io.sve.framework.conf

import pureconfig.generic.ProductHint
import pureconfig.{CamelCase, ConfigConvert, ConfigFieldMapping}
import pureconfig.module.hadoop.pathConvert
import org.apache.hadoop.fs.Path
import pureconfig.configurable.{localDateConfigConvert, localDateTimeConfigConvert}

import java.time.format.DateTimeFormatter

trait CustomPureConfig {

  // CamelCase in Config files
  implicit def hint[A]: ProductHint[A] = ProductHint[A](ConfigFieldMapping(CamelCase, CamelCase))

  // Custom readers
  implicit lazy val hadoopPathConfigReader: ConfigConvert[Path] = pathConvert
  implicit val localDateConvert = localDateConfigConvert(DateTimeFormatter.ISO_DATE)
  implicit val localDateTimeConvert = localDateTimeConfigConvert(DateTimeFormatter.ISO_DATE_TIME)
}
