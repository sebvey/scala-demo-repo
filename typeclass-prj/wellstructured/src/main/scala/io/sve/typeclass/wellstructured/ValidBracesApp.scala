package io.sve.typeclass.wellstructured

import TestSuite._


object ValidBracesApp extends App {

    def validBraces(s: String): Boolean = {

        def op(acc: String,e: Char) = (acc.headOption,e) match {
            case (Some('('),')') | (Some('{'),'}') | (Some('['),']') => acc.tail
            case _ => e +: acc
        }

        s.foldLeft("")(op).isEmpty
    }

    Seq(
        Test("",true),
        Test("(){}",true),
        Test("({}[])",true),
        Test("({[]{}}({{}}))",true),
        Test("({[]{}}({{}})()",false),
        Test("([)]", false),
        Test(")([)]", false)
    ).runOn(validBraces)

}
