package com.demo.scala02

object DefaultParam {
  /*
    如果调用此方法且不给参数，要一个默认值。
   */
  def sum(a: Int = 3,b: Int = 7): Int = {
    a + b
  }

  def main(args: Array[String]): Unit = {
    // 如果传递了参数，则使用传递的值。如果不传递参数，则使用默认值。
    println(sum())
    println(sum(2,4))
  }
}
