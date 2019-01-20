package day06

import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

object JsonSource {
  def main(args: Array[String]): Unit = {
    //1.创建sparkSession
    val sparkSession: SparkSession = SparkSession.builder().appName("JsonSource")
      .master("local[2]").getOrCreate()

    import sparkSession.implicits._
    //2.读取json数据源
    val jread: DataFrame = sparkSession.read.json("e:/saveJson")

    //3.处理数据
    val fread: Dataset[Row] = jread.filter($"xueyuan" === "bigdata")

    //4.触发action
    fread.show()

    //5.关闭资源
    sparkSession.stop()
  }
}
