package io.sve.baseprj.firstjob

import scala.io.Source
import io.sve.baseprj.framework.loader.csv.CsvConf
import scala.util.matching.Regex


object App extends App {

    val str1: String = null
    val str2: String = "hello world"

    def firstNonNullSubGroup(m: Regex.Match): String =
        if (m.group(1) != null) m.group(1) else m.group(2)

    val resource = Source.fromResource("products.csv")
    val csvConf = CsvConf(sep = ",", header = true)

    val lines = resource.getLines
    if (csvConf.header) lines.next

    val pattern = "(?:(?:^|,){1})(?: *)(?:(?=[^ ])([^,^\"]*)(?<=[^ ])|(?:(?:\")([^\"]*)(?:\")))(?: *)(?=(,|$))".r

    for (line <- lines) {

        val lineFields = pattern
            .findAllMatchIn(line)
            .toList
            .map(firstNonNullSubGroup)
        println(s"${lineFields.length} : $lineFields")
    }
}




// TODO : fill here

// find the number of SDA sales for people younger than 50 for year 2023
// le nombre de poele achetées par les ménagères de moins de 50 ans en 2023

// val result = ???
// if (result == 234) println("Yay you win !")
// else println("Try again !")
