package demo

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 需求：计算网页访问量前三名
  * 用户：喜欢视频 直播
  * 帮助企业做经营的决策
  *
  * 看数据
  */
object UrlCount {
  def main(args: Array[String]): Unit = {
    //1.加载数据
    val conf: SparkConf = new SparkConf().setAppName("UrlCount").setMaster("local[2]")
    //spark程序入口
    val sc: SparkContext = new SparkContext(conf)
    //载入数据
    val rdd1: RDD[String] = sc.textFile("e:/itstar.log")

    //2，对数据进行计算 w,1 h,1
    val rdd2: RDD[(String, Int)] = rdd1.map(line => {
      val s: Array[String] = line.split("\t")
      //标注为出现1次
      (s(1), 1)
    })

    //3.将相同的网址进行累加求和
    val rdd3: RDD[(String, Int)] = rdd2.reduceByKey(_+_)

    //4.排序 取出前三
    val rdd4: Array[(String, Int)] = rdd3.sortBy(_._2, false).take(3)

    //5.遍历打印
    rdd3.foreach(x => {
      println("网址为：" + x._1 + "访问量为：" + x._2)
    })

    //6.转换 toString toBuffer
    //println(rdd4.toBuffer)
    sc.stop()
  }
}
