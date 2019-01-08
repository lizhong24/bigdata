import akka.actor.{ActorSystem, Props}

object QqDriver {
  //1.创建ActorSystem 用ActorSystem创建Actor
  private val qqFactory = ActorSystem("QqFactory")
  //2.Actor发送消息通过ActorRef
  private val hRef = qqFactory.actorOf(Props[TomActor],"Tom")
  //John需要接受Tom发送的消息
  private val dRef = qqFactory.actorOf(Props(new JohnActor(hRef)),"John")

  def main(args: Array[String]): Unit = {
    //1.Tom自己给自己发送消息
    //hRef ! "我爱Tom"

    //2John给Tom发送消息
    dRef ! "你好，我是John"
  }
}
