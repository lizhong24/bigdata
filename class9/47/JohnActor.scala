import akka.actor.{Actor, ActorRef}

class JohnActor(val h:ActorRef) extends Actor{
  override def receive: Receive = {
    case "你好，我是John" => {
      //John发送消息给TomActor
      h ! "我爱Tom"
    }
  }
}
