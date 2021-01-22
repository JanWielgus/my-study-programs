package lista12

// Jan Wielgus

import akka.actor._


// zadanie 1

class Player (ID: Int) extends Actor
{
    override def receive = {
        case Player.Ping(pingNum) =>
            println("pong")
            val nextPingNum = pingNum + 1;
            if (nextPingNum < Player.MaxPings) {
                println("ping")
                sender() ! Player.Ping(nextPingNum)
            }

        case other => println(s"Wrong message: $other")
    }
}

object Player {
    def props(ID: Int) = Props(classOf[Player], ID)
    case class Ping(pingNum: Int)
    val MaxPings = 10
}


object Main extends App {
    val actorSystem = ActorSystem("MyActorSystem")
    val pingerOne = actorSystem.actorOf(Player.props(1))
    val pingerTwo = actorSystem.actorOf(Player.props(2))

    pingerOne.tell(Player.Ping(0), pingerTwo)

    Thread.sleep(1000)
    actorSystem.terminate()
}

