package day06

import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

object CsvSource {
  def main(args: Array[String]): Unit = {
    //1.创建sparkSession
    val sparkSession: SparkSession = SparkSession.builder().appName("CsvSource")
      .master("local[2]").getOrCreate()

    import sparkSession.implicits._
    //2.读取csv数据源
    val cread: DataFrame = sparkSession.read.csv("e:/saveCsv")

    //3.处理数据
    val rdf = cread.toDF("id", "xueyuan")
    val rs = rdf.filter($"id" <= 3)

    //4.触发action
    rs.show()

    //5.关闭资源
    sparkSession.stop()
  }
}
