package io.sve.baseprj.simpleconf

import io.sve.framework.conf.CustomPureConfig
import io.sve.baseprj.simpleconf.conf.MyConf
import pureconfig.ConfigSource
import pureconfig.generic.auto._



object Main extends App with CustomPureConfig {

  val conf = ConfigSource.default.loadOrThrow[MyConf]
  println(conf)

    // TODO :
    // trait ConfApp extends App qui permet de lire la conf et la rendre dispo en variable conf
    // lire la conf non pas dans resources mais dans conf/
    // tester la conf
    // faire une Spec qu'on peut étendre pour tester la conf par défaut, puis un fichier donné sur valeur overridée

}
