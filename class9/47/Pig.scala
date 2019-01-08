package com.demo.scala05

object Pig extends Animal {
  //重写eat方法
  override def eat(name: String): Unit = {
    println(s"$name -> 吃吃吃")
  }

  //这里编译报错，final修饰方法不能被重写
  override def sleep(name: String): Unit = {
    println(s"$name -> 做梦吃鸡")
  }

  def main(args: Array[String]): Unit = {
    Pig.eat("猪")
    Pig.sleep("john")
    Pig.name = "john tom"
    println(Pig.name)
  }
}
