package com.demo.scala06

class Compp[T:Ordering](o1:T,o2:T)(implicit comt:Ordering[T]){
  def big = if(comt.compare(o1,o2) > 0) o1 else o2
}

class Personss(val name:String,val age:Int){
  override def toString: String = this.name + "," + this.age
}

//上下文界定 同样发生了隐式转换
object ContextBounds {

  //比较器定义  比较规则
  implicit val comparatorPersonss = new Ordering[Personss]{
    override def compare(x: Personss, y: Personss): Int = x.age - y.age
  }

  def main(args: Array[String]): Unit = {
    val p1 = new Personss("Tom",18)
    val p2 = new Personss("John",15)
    val comc = new Compp(p1, p2)
    println(comc.big)
  }
}
