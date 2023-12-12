package io.sve.baseprj.framework

// https://github.com/MrPowers/spark-fast-tests/

import org.apache.spark.sql.SparkSession
import com.github.mrpowers.spark.fast.tests.DatasetComparer
import com.github.mrpowers.spark.fast.tests.ColumnComparer

trait SparkSpec extends BaseSpec with DatasetComparer with ColumnComparer {

    self: BaseSpec =>

        implicit val spark: SparkSession = {
            val ss = SparkSession
                .builder()
                .master("local[1]")
                .config("spark.sql.shuffle.partitions", "1")
                .config("spark.ui.enabled", "false")
                .getOrCreate()
            ss.sparkContext.setLogLevel("WARN")
            ss
        }
}
