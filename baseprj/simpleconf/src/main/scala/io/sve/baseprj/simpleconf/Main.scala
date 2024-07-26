package io.sve.baseprj.simpleconf

import io.sve.framework.conf.CustomPureConfig
import io.sve.baseprj.simpleconf.conf.MyConf
import pureconfig.ConfigSource
import pureconfig.generic.auto._



object Main extends App with CustomPureConfig {

  val conf = ConfigSource.default.loadOrThrow[MyConf]
  println(conf)

    // TODO :
    // reprendre le fonctionnement du framework SEB, avec un chargeur de conf

}
