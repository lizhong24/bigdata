package test.scala01

//混入特质
object Dog extends Animal with Running {
  override def eat(name: String): Unit = {
    println(s"$name -> 吃骨头")
  }

  override def sleep(name: String): Unit = {
    println(s"$name -> 长膘")
  }

  def main(args: Array[String]): Unit = {
    Dog.eat("小狗")
    Dog.how("金毛")
  }
}
