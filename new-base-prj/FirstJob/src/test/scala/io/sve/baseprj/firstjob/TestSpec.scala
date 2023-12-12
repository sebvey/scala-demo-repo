package io.sve.baseprj.firstjob

import io.sve.baseprj.framework.SparkSpec

class TestSpec extends SparkSpec {

    import spark.implicits._

    "test" should "do something" in {

        val expectedDF = Seq(
            (1,"this is a test"),
            (2,"hello sbt")
        ).toDF("id","desc")

        val actualDF = Seq(
            (1,"this is a test"),
            (2,"hello sbt")
        ).toDF("id","desc")

        assertSmallDatasetEquality(actualDF, expectedDF)
    }

    "test2" should "test columns" in {

        val expectedDF = Seq(
            (1,"this is a test","this is a test"),
            (2,"hello sbt","hello SBT")
        ).toDF("id","desc1","desc2")

        assertColumnEquality(expectedDF, "desc1", "desc2")
    }
}
