package lista13

// Jan Wielgus




// Zadanie 1
class MyPair1[T] (var first: T, var second: T) {
    override def toString: String = s"($first, $second)"
}




// Zadanie 2
abstract class MyPair2 {
    type T
    var first: T
    var second: T
    override def toString: String = s"($first, $second)"
}




// Zadanie 3
class Pracownik private (val surname: String, private var dismissed: Boolean) {
    def zwolnij = {
        Pracownik.liczPrac -= 1
        dismissed = true
    }
    override def toString: String = s"Worker $surname, is ${if (dismissed) "dismissed" else "employed"}"
}

object Pracownik {
    def apply(surname: String): Pracownik = {
        liczPrac = liczPrac + 1
        new Pracownik(surname, false)
    }
    private var liczPrac: Int = 0
    def liczbaPracownikow: Int = liczPrac
}





// Zadanie 4
class Point (private[this] var corX: Int = 0,
             private[this] var corY: Int = 0) {
    def x: Int = corX
    def y: Int = corY
    def x_=(newX: Int): this.type = {
        corX = newX
        this
    }
    def y_=(newY: Int): this.type = {
        corY = newY
        this
    }

    override def toString: String = s"cords: [$x, $y]"
}

class Circle (private[this] var r: Int, x: Int, y: Int) extends Point(x, y) {
    def radius: Int = r
    def radius_=(newRadius: Int): this.type = {
        r = newRadius
        this
    }

    override def toString: String = super.toString + s" radius: $radius"
}

class Cylinder (private[this] var h: Int, r: Int, x: Int, y: Int) extends Circle(r, x, y) {
    def height: Int = h
    def height_=(newHeight: Int): this.type = {
        h = newHeight
        this
    }

    override def toString: String = super.toString + s" height: $height"
}





object Main {

    // Zadanie 5
    def wordCounter(text: String): scala.collection.mutable.Map[String, Int] = {
        val countedWords = scala.collection.mutable.Map[String, Int]()

        text.split(' ').foreach(word => {
            if (countedWords.contains(word))
                countedWords(word) += 1
            else
                countedWords += (word -> 1)
        })

        countedWords
    }




    def main(args: Array[String]): Unit = {
        val testPair1 = new MyPair1("napis", "literki")
        println(testPair1)

        val testPair2 = new MyPair2 {
            type T = Int
            var first = 5
            var second = 6
        }
        println(testPair2)


        println("\n<<<<<<<<<>>>>>>>>>\n")


        val pracownik = Pracownik("Jacek")
        println(pracownik)
        println("Liczba pracownikow: " + Pracownik.liczbaPracownikow)
        Pracownik("Jerzy")
        println("Liczba pracownikow: " + Pracownik.liczbaPracownikow)
        pracownik.zwolnij
        println(pracownik)


        println("\n<<<<<<<<<>>>>>>>>>\n")


        val cylinder = new Cylinder(4, 3, 2, 1)
        println(cylinder)
        ((cylinder.radius = 5).height = 9).x = -10
        println(cylinder)


        println("\n<<<<<<<<<>>>>>>>>>\n")


        println(wordCounter("Ala ma kota ale kota Ala"))
    }
}

