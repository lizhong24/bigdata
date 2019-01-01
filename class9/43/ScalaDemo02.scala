package com.demo.scala01

object ScalaDemo02 {
  def main(args: Array[String]): Unit = {
    println(m1(1,9))
    println(m2(3,8))
  }
  //加法
  def m1(a:Int, b:Int):Int = {
    a + b
  }

  def m2(a:Int,b:Int): Int = a * b
}
