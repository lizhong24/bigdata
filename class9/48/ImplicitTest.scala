package com.demo.scala06

object ImplicitTest {
  //此参数
  def sleep(how:String):Unit = {println(how)}

  //此参数如果被implicit修饰的话，调用可以不写参数 直接sleep2
  def sleep2(implicit how:String = "香啊") = {println(how)}

  def main(args: Array[String]): Unit = {
    sleep("很香")

    sleep2

    implicit val how = "头疼"
    sleep2
  }
}
