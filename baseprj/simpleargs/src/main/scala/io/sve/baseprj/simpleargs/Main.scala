package io.sve.baseprj.simpleargs

import scopt.OParser
import io.sve.baseprj.simpleargs.args.Args

object Main extends App {
    println("Hello SimpleArgs !!")

    OParser.runParser(Args.parser, args, Config()) match {

    }
}

