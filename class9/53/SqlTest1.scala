package day04

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Row, SparkSession}

/**
  * spark2.x
  */
object SqlTest1 {
  def main(args: Array[String]): Unit = {
    //1.构建SparkSession
    val sparkSession = SparkSession.builder().appName("SqlTest1")
      .master("local[2]")
      .getOrCreate()

    //2.创建RDD
    val dataRdd: RDD[String] = sparkSession.sparkContext
      .textFile("hdfs://192.168.146.111:9000/user.txt")

    //3.切分数据
    val splitRdd: RDD[Array[String]] = dataRdd.map(_.split("\t"))

    //4.封装数据
    val rowRdd = splitRdd.map(x => {
      val id = x(0).toInt
      val name = x(1).toString
      val age = x(2).toInt
      //封装一行数据
      Row(id, name, age)
    })

    //5.创建schema（描述DataFrame信息） sql=表
    val schema: StructType = StructType(List(
      StructField("id", IntegerType, true),
      StructField("name", StringType, true),
      StructField("age", IntegerType, true)
    ))

    //6.创建DataFrame
    val userDF: DataFrame = sparkSession.createDataFrame(rowRdd, schema)

    //7.注册表
    userDF.registerTempTable("user_t")

    //8.写sql
    val uSql: DataFrame = sparkSession.sql("select * from user_t order by age")

    //9.查看结果  show databases;
    uSql.show()

    //10.释放资源
    sparkSession.stop()
  }
}
