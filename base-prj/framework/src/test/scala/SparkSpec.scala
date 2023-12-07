package io.sve.framework

import org.apache.spark.sql.SparkSession

trait SparkSpec { self: BaseSpec =>
    implicit val spark: SparkSession = {
        val ss = SparkSession
            .builder()
            .master("local[*]")
            .config("spark.sql.shuffle.partitions", "4")
            .config("spark.ui.enabled", "false")
            .getOrCreate()
        ss.sparkContext.setLogLevel("WARN")
        ss
    }
}
