package edu.sve.ssb

import org.apache.spark.sql._

object ScalaSparkBaseApp extends App {

    override def main(args: Array[String]): Unit = {

        val spark = SparkSession.builder()
            .master("local[1]")
            .appName("SbtSparkBase")
            .getOrCreate

        // Remove Spark INFO/WARN logs (after this spark session is created)
        spark.sparkContext.setLogLevel("WARN")

        var inPath = this.getClass.getResource("/titanic-dataset.csv").getPath

        val df = spark.read
            .option("header","true")
            .csv(inPath)

        df.show(false)

    }


}
