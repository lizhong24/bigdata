package com.demo.scala05

trait Animal {
  //定义姓名
  final var name:String = "tom"
  var age:Int = 18

  def eat(name: String)

  def sleep(name: String): Unit = {
    println(s"$name -> 睡得天花乱坠")
  }
}
