package day06

import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

/**
  * mysql作为数据源
  *
  * schema信息
  * root
  * |-- uid: integer (nullable = false)
  * |-- xueyuan: string (nullable = true)
  * |-- number_one: string (nullable = true)
  */
object JdbcSource {
  def main(args: Array[String]): Unit = {
    //1.sparkSQL 创建sparkSession
    val sparkSession: SparkSession = SparkSession.builder().appName("JdbcSource")
      .master("local[2]").getOrCreate()

    //2.加载数据源
    val urlData: DataFrame = sparkSession.read.format("jdbc").options(Map(
      "url" -> "jdbc:mysql://localhost:3306/urlcount",
      "driver" -> "com.mysql.jdbc.Driver",
      "dbtable" -> "url_data",
      "user" -> "root",
      "password" -> "root"
    )).load()

    //测试
    //urlData.printSchema()
    //urlData.show()

    //3.过滤数据
    val fData: Dataset[Row] = urlData.filter(x => {
      //uid>2 如何拿到uid？
      x.getAs[Int](0) > 2
    })

    fData.show()
    sparkSession.stop()
  }
}
