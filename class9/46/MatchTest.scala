package test.scala01

//模式匹配
object MatchTest {
  def main(args: Array[String]): Unit = {
    def strMatch(str:String) = str match {
      case "john" => println("很帅")
      case "mary" => println("很美")
      case _ => println("你是谁？")
    }
    strMatch("john")

    def arrayMatch(arr:Any) = arr match {
      case Array(1) => println("只有一个元素的数组")
      case Array(1,2) => println("有两个元素的数组")
    }
    arrayMatch(Array(1,2))

    def tuple(tuple:Any) = tuple match {
      case (1,_) => println("元组的第一个元素为1，第二个元素任意")
      case ("tom",18) => println("这是个帅小伙")
    }
    tuple("tom",18)
  }
}
