package com.demo.scala06

//视图界定
class CompareCC[T <% Comparable[T]](o1:T,o2:T){
  def big = if(o1.compareTo(o2) > 0) o1 else o2
}

object ViewBounds {
  def main(args: Array[String]): Unit = {
    //视图界定发生了隐式转换
    val comc = new CompareCC(1,2)
    println(comc.big)
  }
}
