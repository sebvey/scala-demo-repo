package io.sve.baseprj.framework

import org.apache.spark.sql.SparkSession

trait SparkSpec extends BaseSpec {

    self: BaseSpec =>

        implicit val spark: SparkSession = {
            val ss = SparkSession
                .builder()
                .master("local[1]")
                .config("spark.sql.shuffle.partitions", "4")
                .config("spark.ui.enabled", "false")
                .getOrCreate()
            ss.sparkContext.setLogLevel("WARN")
            ss
        }
}
