package test.scala01

object ScalaDemo {
  def main(args: Array[String]): Unit = {
    //被private 修饰的主构造器 对外访问权限
    //val p = new Person4("hh",88)
    //val p2 = new Person4("ff",33,190)
    //println(p.name)
    //println(p2.name)
    val p = new Person5("hh",11,110)
    println(p.name)
  }
}
