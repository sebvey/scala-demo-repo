package io.sve.pureconfig.simpleconf


import io.sve.framework.app.AppWithConf
import pureconfig._
import pureconfig.generic.auto._
import io.sve.pureconfig.simpleconf.conf.MyConf
import io.sve.framework.conf.CustomPureConfig



object Main extends AppWithConf {

    println("Hello from ZimpleConf")

    val conf = ConfigSource.default.loadOrThrow[MyConf]
    println(conf)

    // TODO :
    // trait ConfApp extends App qui permet de lire la conf et la rendre dispo en variable conf
    // lire la conf non pas dans resources mais dans conf/
    // tester la conf
    // faire une Spec qu'on peut étendre pour tester la conf par défaut, puis un fichier donné sur valeur overridée
    // Plus tard : faire un reader pour un Path hadoop

}
