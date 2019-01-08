package com.demo.scala05

//样例类支持模式匹配
case class Eat(food:String,drink:String)

object TestEat {
  def main(args: Array[String]): Unit = {
    val eat = new Eat("麻辣香锅","北冰洋")
    println(eat)
  }
}
