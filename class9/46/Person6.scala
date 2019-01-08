package test.scala01

class Person6(private var name:String,age:Int) {
  var high:Int = _

  def this(name:String,age:Int,high:Int){
    this(name,age)
    this.high = high
  }
}

//注意：在伴生对象中可以访问类的私有成员方法和属性
//什么是伴生对象？ 单例类名和类名相同
object Person6 extends App{
  val p = new Person6("tom",18,180)
  println(p.name)
}
