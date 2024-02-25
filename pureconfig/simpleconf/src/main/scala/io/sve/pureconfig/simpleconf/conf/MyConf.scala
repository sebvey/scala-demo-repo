package io.sve.pureconfig.simpleconf.conf

import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import MyConf._
import org.apache.hadoop.fs.Path

case class MyConf(
  name: String,
  age: Age,
  sex: Sex,
  nicknames: Seq[String],
  maturity: Double,
  mantra: Option[String],
  petsList: List[Pet],
  myPath: Path
)

object MyConf {

  // case classes are read out of the box
  case class Age(age: Int) extends AnyVal

  sealed trait Pet
  object Pet {
    case class Cat(name: String) extends Pet
    case class Rabbit(name: String, likesCarrots: Boolean) extends Pet
  }

  sealed trait Sex
  object Sex {
    implicit val sexReader: ConfigReader[Sex] = deriveEnumerationReader[Sex]
    case object Male extends Sex
    case object Female extends Sex
  }
}
