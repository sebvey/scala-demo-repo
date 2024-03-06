package io.sve.baseprj.simpleargs

import scopt.OParser
import io.sve.baseprj.simpleargs.args.ArgsRepr
import shapeless.Default

object Main extends App {

    println("Hello SimpleArgs !!")

    val argsRepr = OParser.parse(ArgsRepr.parser, args, ArgsRepr())
        .getOrElse(throw new RuntimeException("Can't parse arguments"))

  println(argsRepr.nickname)
}
