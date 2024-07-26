package io.sve.baseprj.simpleargs.args

import java.time.LocalDateTime
import scopt.{OParser, OParserBuilder}
import io.sve.framework.args.CustomScoptRead

// We need default value
// During parsing process, an initial object is created, and
// passed around as an argument into action callbacks

case class ArgsRepr(
  nickname: String = null,
  mainDatetime: LocalDateTime = null
)

// bellow, opt[String](...).action() allows us to 'transform' our args object
// thanks to the parsed String

// LocalDateTime can't be parsed by default
// we could have used String and converts it to LocalDateTime here ...
// The 'framework' trait 'CustomScoptRead' provides a Reader[LocalDateTime]

object ArgsRepr extends CustomScoptRead {
  val builder: OParserBuilder[ArgsRepr] = OParser.builder[ArgsRepr]
  val parser: OParser[Unit, ArgsRepr] = {
    import builder._
    OParser.sequence(
      programName("simpleargs"),
      head("SimpleArgs", "1.0"),
      opt[String]('n', "nickname")
        .action((x, args) => args.copy(nickname = x))
        .text("nickname is a string property"),
      opt[LocalDateTime]("mainDatetime")
        .required()
        .action((x, args) => args.copy(mainDatetime = x))
    )

  }
}
