package io.sve.sparkbasics

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{avg}
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.types.DoubleType


object SparkBasicsApp extends App {

    val spark = SparkSession
        .builder
        .master("local[1]")
        .appName("SparkBasicsApp")
        .getOrCreate

        import spark.implicits._

    // set logs to WARN
    spark.sparkContext.setLogLevel("WARN")

    // print spark session config
    spark.conf.getAll.foreach(println)

    val srcPath = this.getClass().getResource("/titanic-dataset.csv").getPath

    val df = spark.read
        .option("header","true")
        .csv(srcPath)

    df
        .groupBy($"Sex",$"Survived")
        .count()
        .show(false)
}
