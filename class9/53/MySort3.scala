package day04

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

//定义一个专门处理隐式的类
object ImplicitRules {
  //定义隐式规则
  implicit object OrderingGirl extends Ordering[Girl1]{
    override def compare(x: Girl1, y: Girl1): Int = {
      if(x.age == y.age){
        //体重重的往前排
        -(x.weight - y.weight)
      }else{
        //年龄小的往前排
        x.age - y.age
      }
    }
  }
}

object MySort3 {
  def main(args: Array[String]): Unit = {
    //1.spark程序的入口
    val conf: SparkConf = new SparkConf().setAppName("MySort1").setMaster("local[2]")
    val sc: SparkContext = new SparkContext(conf)

    //2.创建数组
    val girl: Array[String] = Array("reba,18,80","mimi,22,100","liya,30,80","jingtian,18,78")

    //3.转换RDD
    val grdd1: RDD[String] = sc.parallelize(girl)

    //4.切分数据
    val grdd2 = grdd1.map(line => {
      val fields: Array[String] = line.split(",")

      //拿到每个属性
      val name = fields(0)
      val age = fields(1).toInt
      val weight = fields(2).toInt

      //元组输出
      (name, age, weight)
    })

    import ImplicitRules.OrderingGirl
    val sorted = grdd2.sortBy(s => Girl1(s._1, s._2, s._3))
    val r = sorted.collect()
    println(r.toBuffer)
    sc.stop()
  }
}

//自定义类 scala Ordered
case class Girl1(val name: String, val age: Int, val weight: Int)

