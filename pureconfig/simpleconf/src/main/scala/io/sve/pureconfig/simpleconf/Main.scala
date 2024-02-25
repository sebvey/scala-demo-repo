package io.sve.pureconfig.simpleconf


import pureconfig._
import pureconfig.generic.auto._
import io.sve.pureconfig.simpleconf.conf.MyConf



object Main extends App {

    println("Hello from ZimpleConf")

    // overrides default ConfigReader[String], to get lower strings
    // implicit val strReader: ConfigReader[String] = ConfigReader.fromString[String](catchReadError(_.toLowerCase))

    // overrides naming convention : from camelCase for fields in conf file, to camelCase for fields in fields in Scala Object
    // implicit def hint[A]: ProductHint[A] = ProductHint[A](ConfigFieldMapping(CamelCase, CamelCase))

    val conf = ConfigSource.default.loadOrThrow[MyConf]
    println(conf)

    // TODO :
    // trait ConfApp extends App qui permet de lire la conf et la rendre dispo en variable conf
    // lire la conf non pas dans resources mais dans conf/
    // tester la conf
    // faire une Spec qu'on peut étendre pour tester la conf par défaut, puis un fichier donné sur valeur overridée
    // Plus tard : faire un reader pour un Path hadoop

}
