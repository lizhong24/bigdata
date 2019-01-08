package test.scala01

trait Animal {
  //定义未实现的方法
  def eat(name:String)

  //定义实现的方法
  def sleep(name:String): Unit = {
    println(s"$name -> 在睡觉")
  }
}
