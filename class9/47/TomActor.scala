import akka.actor.Actor

class TomActor extends Actor{
  override def receive: Receive = {
    case "你好，我是John" => {
      println("你好，我是Tom")
    }

    case "我爱Tom" => {
      println("Tom也爱John")
    }
  }
}
