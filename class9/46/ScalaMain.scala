package test.scala01

/**
  * 在scala中object是一个单例对象
  * 在scala中object定义的成员变量和方法都是静态的
  * 可以通过 类名. 来进行调用
  */
object ScalaMain {
  def main(args: Array[String]): Unit = {
    println(ScalaTest.name)
    ScalaTest.sleep("Tom睡得很香！")
  }
}
