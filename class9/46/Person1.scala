package test.scala01

//定义类
class Person1 {
  //定义姓名 年龄
  var name: String = _
  var age: Int = _
}

//继承App特质 可以不写main
object Test extends App{
  val p = new Person1
  p.name = "tom"
  p.age = 18
  println(p.name)
}
