package com.demo.scala02

/**
  * 传值调用
  * 1.先计算balance的值
  * 2.把这个值作为参数传递给printMoney
  */
object ZFBToPay {
  var money = 1000

  // 吃饭一次花50元
  def eat(): Unit = {
    money = money - 50
  }

  // 余额
  def balance(): Int = {
    //已调用一次eat方法
    eat()
    money
  }

  def printMoney(x: Int): Unit = {
    for(a <- 1 to 5){
      println(f"你的余额现在为：$x")
    }
  }

  def main(args: Array[String]): Unit = {
    eat()
    balance()
    printMoney(balance())
  }
}
