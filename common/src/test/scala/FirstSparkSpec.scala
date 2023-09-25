import io.sve.framework.{BaseSpec, SparkSpec}


class FirstSparkSpec extends BaseSpec with SparkSpec {

    it should "do the trick" in {
        spark.conf.getAll.foreach(println)
    }

}
