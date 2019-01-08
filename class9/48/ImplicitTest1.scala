package com.demo.scala06

//隐式转换类型
object ImplicitTest1 {
  //类型转换
  implicit def double2Int(d:Double) = {d.toInt}

  def main(args: Array[String]): Unit = {
    val a:Int = 18.8
    println(a)
  }
}
