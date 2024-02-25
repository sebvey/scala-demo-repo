package io.sve.framework.conf

import pureconfig.generic.ProductHint
import pureconfig.ConfigFieldMapping
import pureconfig.CamelCase
import pureconfig.ConfigConvert
import pureconfig.module.hadoop.pathConvert
import org.apache.hadoop.fs.Path

trait CustomPureConfig {

  // CamelCase in Config files
  implicit def hint[A]: ProductHint[A] = ProductHint[A](ConfigFieldMapping(CamelCase, CamelCase))

  // Custom readers
  implicit lazy val hadoopPathConfigReader: ConfigConvert[Path] = pathConvert
}
