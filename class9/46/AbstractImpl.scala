package test.scala01

//继承抽象类可以再继承特质 但是抽象类写在前 用with连接
object AbstractImpl extends AbstractDemo with Running {
  override def eat(food: String): Unit = {
    //ctrl + i
    println(s"$food -> 吃火锅")
  }

  def main(args: Array[String]): Unit = {
    AbstractImpl.eat("tom")
    AbstractImpl.how("john")
  }
}
