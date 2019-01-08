import akka.actor.{Actor, ActorSystem, Props}

object CallMe {
  //1.创建ActorSystem 用ActorSystem创建Actor
  private val acFactory = ActorSystem("AcFactory")
  //2.Actor发送消息通过ActorRef
  private val callRef = acFactory.actorOf(Props[CallMe],"CallMe")

  def main(args: Array[String]): Unit = {
    //3.发送消息
    callRef ! "你吃饭了吗"
    callRef ! "很高兴见到你"
    callRef ! "stop"
  }
}

class CallMe extends Actor{
  //Receive用户接收消息并且处理消息
  override def receive: Receive = {
    case "你吃饭了吗" => println("吃的鸡腿")
    case "很高兴见到你" => println("我也是")
    case "stop" => {
      //关闭代理ActorRef
      context.stop(self)
      //关闭ActorSystem
      context.system.terminate()
    }
  }
}
