import akka.actor._

// Jan Wielgus

class Server(N: Int) extends Actor {
    println(s"Guess my number from the interval [0..$N]")
    private val toGuess = scala.util.Random.nextInt(N);

    override def receive = {
        case Server.M(guess) =>
            if (guess > toGuess)
                sender() ! Client.R("tooBig", guess)
            else if (guess < toGuess)
                sender() ! Client.R("tooSmall", guess)
            else
                sender() ! Client.R("goodGuess", guess)

        case msg => sender() ! s"Wrong request: '$msg'"
    }
}

object Server {
    def props(N: Int) = Props(classOf[Server], N)
    case class M(guess: Int)
}


class Client(name: String, server: ActorRef, private var guessUpperLimit: Int) extends Actor {
    private var guessBottomLimit = 0

    override def receive = {
        case Client.Start =>
            println(name + " starting")
            server ! Server.M(50)

        case Client.R(response, guessedNum) =>
            response match {
                case "tooBig" =>
                    guessUpperLimit = guessedNum
                    val newTry = (guessUpperLimit + guessBottomLimit) / 2;
                    println(s"$name: Response: too big. I'm trying $newTry");
                    server ! Server.M(newTry)
                case "tooSmall" =>
                    guessBottomLimit = guessedNum
                    val newTry = (guessUpperLimit + guessBottomLimit) / 2;
                    println(s"$name: Response: too small. I'm trying $newTry");
                    server ! Server.M(newTry)
                case "goodGuess" =>
                    println(s"$name: I guessed it! $guessedNum \nGoodbye! from $name")
                    context.system.terminate()
                case otherResponse => println(s"$name: Invalid response: $otherResponse")
            }

        case other => println(s"Wrong response: $other");
    }
}

object Client {
    def props(name: String, server: ActorRef, bound: Int) = Props(classOf[Client], name, server, bound)
    case class R(response: String, guessedNum: Int)
    case object Start
}


object Main extends App {
    val actorSystem = ActorSystem("MyActorSystem")
    val server: ActorRef = actorSystem.actorOf(Server.props(100), "MyServer")
    val client1: ActorRef = actorSystem.actorOf(Client.props("Client1", server, 100));
    val client2: ActorRef = actorSystem.actorOf(Client.props("Client2", server, 100));

    client1 ! Client.Start
    client2 ! Client.Start

    Thread.sleep(1000);
    actorSystem.terminate();
}



