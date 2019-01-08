package test.scala01

/**
  * 类的前面如果加上包名，表示当前类在当前包及其子包可见，可以访问
  * [this}默认是它，表示当前包都有访问权限
  */
private[this] class Person5(var name:String,age:Int) {
  var high:Int = _

  def this(name:String,age:Int,high:Int){
    this(name,age)
    this.high = high
  }
}
