package com.demo.scala02

object GaoJie {
  // 将其他函数作为参数，或者其结果是函数的函数
  def getPerson(h: Int => String,f: Int): String = {
    //函数h 参数f
    h(f)
  }

  def Person(x: Int) = "我是" + x.toString + "岁很帅的lz"

  def main(args: Array[String]): Unit = {
    println(getPerson(Person,18))
  }
}
