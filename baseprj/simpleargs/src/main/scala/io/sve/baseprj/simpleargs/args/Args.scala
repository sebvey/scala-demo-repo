package io.sve.baseprj.simpleargs.args

import java.time.LocalDateTime
import scopt.OParser
import io.sve.framework.args.CustomScoptRead

// We need default value
// During parsing process, an initial object is created, and
// passed around as an argument into action callbacks

case class Args(
  nickName: String = null,
  mainDateTime: LocalDateTime = null
)

// bellow, opt[String](...).action() allows us to 'transform' our args object
// thanks to the parsed String
// LocalDateTime can't be parsed by default, we could have used String and
// converts it to LocalDateTime
// For better readability we have provided a Reader[LocalDateTime]
// in trait CustomScoptRead

object Args extends CustomScoptRead {
  val builder = OParser.builder[Args]
  val parser  = {
    import builder._
    OParser.sequence(
      programName("simpleargs"),
      head("SimpleArgs", "1.0"),
      opt[String]('n', "name")
        .action((x, args) => args.copy(nickName = x))
        .text("foo is a string property"),
      opt[LocalDateTime]("mainDatetime")
        .required()
        .action((x, args) => args.copy(mainDateTime = x))
    )

  }
}
