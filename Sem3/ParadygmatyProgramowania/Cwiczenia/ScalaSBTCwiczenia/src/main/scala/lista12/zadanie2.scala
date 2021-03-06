package lista12

import akka.actor._

// Zadnaie 2

package zadanie2 {
    class Player (val num: Int, val players: Array[ActorRef]) extends Actor {
        override def receive = {
            case Player.Ball(count) =>
                println(s"Throws counter: $count. Throwing player number $num")
                val nextPlayerIdx = Player.rand.nextInt(players.length - 1)
                val newBallMessage = Player.Ball(count + 1)
                if (nextPlayerIdx < num)
                    players(nextPlayerIdx) ! newBallMessage
                else
                    players(nextPlayerIdx + 1) ! newBallMessage

            case other => println(s"Invalid message: $other")
        }
    }

    object Player {
        def props(num: Int, players: Array[ActorRef]) = Props(classOf[Player], num, players)
        case class Ball(count: Int)
        private val rand = scala.util.Random
    }



    object Main extends App {
        val actorSystem = ActorSystem("MyActorSystem")
        val players = new Array[ActorRef](3)
        players(0) = actorSystem.actorOf(Player.props(0, players))
        players(1) = actorSystem.actorOf(Player.props(1, players))
        players(2) = actorSystem.actorOf(Player.props(2, players))

        players(0).tell(Player.Ball(0), players(1))

        Thread.sleep(1000);
        actorSystem.terminate()
    }
}
