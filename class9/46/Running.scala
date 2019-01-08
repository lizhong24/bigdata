package test.scala01

trait Running {
  def how(str:String): Unit = {
    println(s"$str -> 在奔跑")
  }
}
