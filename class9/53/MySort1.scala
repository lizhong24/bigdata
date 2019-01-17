package day04

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 实现自定义的排序
  */
object MySort1 {
  def main(args: Array[String]): Unit = {
    //1.spark程序的入口
    val conf: SparkConf = new SparkConf().setAppName("MySort1").setMaster("local[2]")
    val sc: SparkContext = new SparkContext(conf)

    //2.创建数组
    val girl: Array[String] = Array("reba,18,80","mimi,22,100","liya,30,80","jingtian,18,78")

    //3.转换RDD
    val grdd1: RDD[String] = sc.parallelize(girl)

    //4.切分数据
    val grdd2: RDD[Girl] = grdd1.map(line => {
      val fields: Array[String] = line.split(",")

      //拿到每个属性
      val name = fields(0)
      val age = fields(1).toInt
      val weight = fields(2).toInt

      //元组输出
      //(name, age, weight)
      new Girl(name, age, weight)
    })

//    val sorted: RDD[(String, String, Int)] = grdd2.sortBy(t => t._2, false)
//    val r: Array[(String, String, Int)] = sorted.collect()
//    println(r.toBuffer)

    val sorted: RDD[Girl] = grdd2.sortBy(s => s)
    val r = sorted.collect()
    println(r.toBuffer)
    sc.stop()
  }
}

//自定义类 scala Ordered
class Girl(val name: String, val age: Int, val weight: Int) extends Ordered[Girl] with Serializable {
  override def compare(that: Girl): Int = {
    //如果年龄相同 体重重的往前排
    if(this.age == that.age){
      //如果正数 正序 负数 倒序
      -(this.weight - that.weight)
    }else{
      //年龄小的往前排
      this.age - that.age
    }

  }
  override def toString: String = s"名字:$name,年龄:$age,体重:$weight"
}

