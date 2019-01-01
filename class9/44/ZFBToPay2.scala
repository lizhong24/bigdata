package com.demo.scala02

/**
  * 传名调用
  * 传递的是函数
  * 将balance方法的名称传递到方法内部执行
  */
object ZFBToPay2 {
  var money = 1000

  // 吃饭一次花50元
  def eat(): Unit = {
    money = money - 50
  }

  // 余额
  def balance(): Int = {
    // 已调用一次eat方法
    eat()
    money
  }

  // 此时余额减了5次 x: => Int 表示的是一个方法的签名 没有参数 返回值是Int类型的函数
  def printMoney(x: => Int): Unit = {
    for(a <- 1 to 5){
      println(f"你的余额现在为：$x")
    }
  }

  def main(args: Array[String]): Unit = {
    // eat()
    // balance()
    printMoney(balance())
  }
}
