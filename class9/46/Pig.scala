package test.scala01

object Pig extends Animal {
  override def eat(name: String): Unit = {
    println(s"$name -> 在吃饭")
  }

  override def sleep(name: String): Unit = {
    println(s"$name -> 做梦吃鸡")
  }

  def main(args: Array[String]): Unit = {
    Pig.eat("john")
    Pig.sleep("tom")
  }
}
