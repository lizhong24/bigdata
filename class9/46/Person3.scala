package test.scala01

class Person3(var name:String,age:Int) {
  var high:Int = _
  var weight:Int = _

  //定义辅助构造器
  def this(name:String,age:Int,high:Int){
    //注意：在辅助构造器中必须先调用主构造器
    this(name,age)
    this.high = high
  }

  //辅助构造器可以是多个
  def this(name:String,age:Int,high:Int,weight:Int){
    /*
      如果主构造器中成员变量属性没有被val var修饰的话，该属性不能被访问
      相当于java中没有对外提供get方法

      如果成员属性使用var修饰的话，相当于java中对外提供了get方法和set方法
      如果成员属性使用val修饰的话，相当于java中对外只提供了get方法
     */
    this(name,age)
    this.weight = weight
  }
}

object Test3 extends App{
  val p1 = new Person3("tom",18)
  println(p1.name)
}
