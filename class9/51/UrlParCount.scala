package day02

import java.net.URL

import org.apache.spark.rdd.RDD
import org.apache.spark.{Partitioner, SparkConf, SparkContext}

import scala.collection.mutable

/**
  * 需求：加入自定义分区
  * 按照学院分区，相同的学院分为一个结果文件
  */
object UrlParCount {
  def main(args: Array[String]): Unit = {
    //1.创建sparkContext
    val conf: SparkConf = new SparkConf().setAppName("UrlParCount").setMaster("local[2]")
    val sc: SparkContext = new SparkContext(conf)

    //2.加载数据
    val rdd1 = sc.textFile("e:/access.log").map(line => {
      val s: Array[String] = line.split("\t")
      //元组输出
      (s(1), 1)
    })

    //3.聚合
    val rdd2: RDD[(String, Int)] = rdd1.reduceByKey(_+_)

    //4.自定义格式
    val rdd3: RDD[(String, (String, Int))] = rdd2.map(t => {
      val url = t._1
      val host = new URL(url).getHost
      val xHost: String = host.split("[.]")(0)
      //元组输出
      (xHost, (url, t._2))
    })

    //5.加入自定义分区
    val xueyuan: Array[String] = rdd3.map(_._1).distinct().collect
    val xueYuanPartitioner: XueYuanPartitioner = new XueYuanPartitioner(xueyuan)

    //6.加入分区规则
    val rdd4: RDD[(String, (String, Int))] = rdd3.partitionBy(xueYuanPartitioner).mapPartitions(it => {
      it.toList.sortBy(_._2._2).reverse.take(1).iterator
    })

    //7.遍历打印
    rdd4.saveAsTextFile("e://pout")

    //8.关闭资源
    sc.stop()
  }
}

class XueYuanPartitioner(xy: Array[String]) extends Partitioner {
  //自定义规则 学院 分区号
  val rules: mutable.HashMap[String, Int] = new mutable.HashMap[String, Int]()
  var number = 0

  //遍历学院
  for(i <- xy){
    //学院与分区号对应
    rules += (i -> number)
    //分区号递增
    number += 1
  }

  //总的分区个数
  override def numPartitions: Int = xy.length

  //拿到分区
  override def getPartition(key: Any): Int = {
    rules.getOrElse(key.toString, 0)
  }
}
