package test.scala01

//定义抽象类
abstract class AbstractDemo {
  def eat(food:String)

  def sleep(how:String): Unit = {
    println(s"$how -> 睡得很香")
  }
}
