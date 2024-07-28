package io.sve.framework.xdf

import io.sve.framework.spec.BaseSpec
import org.apache.spark.sql.types.{IntegerType, StringType}

class SBSpec extends BaseSpec {

  it should "work" in {

    val myDF = DFNavigator()

    println(myDF.image.url.$.name)

  }
}
