// Jan Wielgus



// zadanie 1

def zgadnij (): Unit = {
    val r = scala.util.Random

    def askForNumber(): Int = {
        println("Podaj liczbe: ")
        scala.io.StdIn.readInt()
    }

    val random = r.nextInt(101)
    var choosen = askForNumber()

    while (choosen != random) {
        if (choosen > random)
            println("Moja jest mniejsza")
        else
            println("Moja jest wieksza")
        choosen = askForNumber()
    }

    println("Zgadles. Brawo!")
}

zgadnij()







