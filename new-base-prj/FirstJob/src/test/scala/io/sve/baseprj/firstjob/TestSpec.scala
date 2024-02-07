package io.sve.baseprj.firstjob

import io.sve.baseprj.framework.SparkSpec

class TestSpec extends SparkSpec {

    import spark.implicits._

    it should "compare two small DF" in {

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

    it should "compare two Columns of a DF" in {

        val expectedDF = Seq(
            (1,"this is a test","this is a test"),
            (2,"hello sbt","hello sbt")
        ).toDF("id","desc1","desc2")

        assertColumnEquality(expectedDF, "desc1", "desc2")
    }
}
