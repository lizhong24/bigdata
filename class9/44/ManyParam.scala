package com.demo.scala02

object ManyParam {
  // scala中的可变参数
  def sum(ints: Int*): Int = {
    var sum = 0
    for(v <- ints){
      sum += v
    }
    sum
  }

  /*
    不仅是可变参
    而且参数的类型不一致
    Any
   */
  def setName(params: Any*): Any = {
    return params
  }

  def main(args: Array[String]): Unit = {
    println(sum(1,2,3,4))
    println(sum(2,3))
    println(setName("John",18,188))
  }
}
