package com.demo.scala02

object PartialFunction {

  //定义函数
  def func(str: String): Int = {
    if(str.equals("lz")) 18 else 0
  }

  //定义偏函数
  def func1:PartialFunction[String, Int] = {
    //如果使用了偏函数必须用case
    case "lz" => 18
      //如果其他
    case _ => 0
  }

  def main(args: Array[String]): Unit = {
    println(func("lz"))
    println(func1("Lz"))
    println(func1("lz"))
  }
}
