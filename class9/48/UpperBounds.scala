package com.demo.scala06

//定义一个比较的方式
class CompareInt(a:Int,b:Int){
  def compare = if(a > b) a else b
}

//定义比较类型
class CompareC[T <: Comparable[T]](o1:T,o2:T){
  def compare = if(o1.compareTo(o2) > 0) o1 else o2
}

object UpperBounds {
  def main(args: Array[String]): Unit = {
    val big = new CompareInt(1,2)
    System.out.println(big.compare)

    val comc = new CompareC(Integer.valueOf(1),Integer.valueOf(2))
    System.out.println(comc.compare)
  }
}
