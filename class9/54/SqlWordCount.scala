package day05

import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

object SqlWordCount {
  def main(args: Array[String]): Unit = {
    //1.创建SparkSession
    val sparkSession: SparkSession = SparkSession.builder()
      .appName("SqlWordCount")
      .master("local[2]")
      .getOrCreate()

    //2.加载数据 使用dataSet处理数据 dataSet是一个更加智能的rdd，默认有一列叫value
    val datas: Dataset[String] = sparkSession.read
      .textFile("hdfs://192.168.146.111:9000/words.txt")

    //3.sparkSql 注册表/注册视图 rdd.flatMap
    import sparkSession.implicits._
    val word: Dataset[String] = datas.flatMap(_.split("\t"))

    //4.注册视图
    word.createTempView("wc_t")

    //5.执行sql wordCount
    val r: DataFrame = sparkSession
      .sql("select value as word,count(*) sum from wc_t group by value order by sum desc")

    r.show()
    sparkSession.stop()
  }
}
