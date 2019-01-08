package com.demo.scala06

object ScalaTest {
  def main(args: Array[String]): Unit = {
    val p = new Person[Int,Double,Double](18,165.5,99)
    println(p.high)
  }
}
