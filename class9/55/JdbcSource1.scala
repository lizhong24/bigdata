package day06

import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

object JdbcSource1 {
  def main(args: Array[String]): Unit = {
    //1.sparkSQL 创建sparkSession
    val sparkSession: SparkSession = SparkSession.builder().appName("JdbcSource")
      .master("local[2]").getOrCreate()

    import sparkSession.implicits._
    //2.加载数据源
    val urlData: DataFrame = sparkSession.read.format("jdbc").options(Map(
      "url" -> "jdbc:mysql://localhost:3306/urlcount",
      "driver" -> "com.mysql.jdbc.Driver",
      "dbtable" -> "url_data",
      "user" -> "root",
      "password" -> "root"
    )).load()

    //3.uid>2
    val r = urlData.filter($"uid" > 2)
    val rs: DataFrame = r.select($"xueyuan", $"number_one")

    //val rs: DataFrame = r.select($"xueyuan")

    //写入以text格式
    //rs.write.text("e:/saveText")

    //写入以json格式
    //rs.write.json("e:/saveJson")

    //写入以csv格式
    rs.write.csv("e:/saveCsv")

    //rs.write.parquet("e:/savePar")

    rs.show()
    sparkSession.stop()
  }
}
