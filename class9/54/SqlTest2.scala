package day05

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

/**
  * DSL风格
  */
object SqlTest2 {
  def main(args: Array[String]): Unit = {
    //1.创建sparkSession
    val sparkSession: SparkSession = SparkSession.builder()
      .appName("SqlTest2")
      .master("local[2]")
      .getOrCreate()

    //2.创建rdd
    val dataRDD: RDD[String] = sparkSession.sparkContext
      .textFile("hdfs://192.168.146.111:9000/user.txt")

    //3.切分数据
    val splitRDD: RDD[Array[String]] = dataRDD.map(_.split("\t"))
    val rowRDD: RDD[Row] = splitRDD.map(x => {
      val id = x(0).toInt
      val name = x(1).toString
      val age = x(2).toInt
      //Row代表一行数据
      Row(id, name, age)
    })

    val schema: StructType = StructType(List(
      //结构字段
      StructField("id", IntegerType, true),
      StructField("name", StringType, true),
      StructField("age", IntegerType, true)
    ))

    //4.rdd转换为dataFrame
    val userDF: DataFrame = sparkSession.createDataFrame(rowRDD, schema)

    //5.DSL风格 查询年龄大于18 rdd dataFrame dataSet
    import sparkSession.implicits._
    val user1DF: Dataset[Row] = userDF.where($"age" > 18)
    user1DF.show()

    //6.关闭资源
    sparkSession.stop()

  }
}
