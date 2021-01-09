package lista9.zadanie4

// Jan Wielgus


object UzycieWyjatkow {
    def main(args: Array[String]): Unit = {
        try {
            metoda1()
        } catch {
            case e: Throwable => {
                println(e.getMessage + "\n")
                e.printStackTrace()
            }
        }
    }

    def metoda1(): Unit = metoda2()

    def metoda2(): Unit = metoda3()

    def metoda3(): Unit = throw new Exception("Wyjatek zgloszony w metoda 3")
}
