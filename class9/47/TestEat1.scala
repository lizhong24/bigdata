package com.demo.scala05

//样例类支持模式匹配
case class Boy(high:Int,weight:Int)
case class Girl(high:Int,weight:Int)

object TestEat1 extends App {
  def objMatch(obj:Any) = obj match {
    case Boy(x,y) => println(s"$x $y 的男孩")
    case Girl(x,y) => println(s"$x $y 的女孩")
  }

  objMatch(Boy(180,120))
  objMatch(Girl(160,90))
}
