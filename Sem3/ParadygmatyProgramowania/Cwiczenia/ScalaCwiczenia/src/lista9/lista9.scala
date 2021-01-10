package lista9

// Jan Wielgus



// zadanie 1

class Time1(_hour: Int)
{
    hour = _hour

    private[this] var h: Int = _
    def hour: Int = h
    def hour_=(newHour: Int): Unit = {
        require(newHour < 24, s"newHour = $newHour")
        if (newHour < 0) h = 0
        else h = newHour
    }
}

object Time1
{
    def apply(h: Int): Time1 = new Time1(h)
}




// zadanie 2a

class Time2a(_hour: Int, _minute: Int) {
    private[this] var hrs: Int = _
    private[this] var mins: Int = _
    hour = _hour
    minute = _minute

    def hour: Int = hrs
    def hour_=(newHour: Int): Unit = {
        require(newHour >= 0 && newHour < 24, s"newHour = $newHour")
        hrs = newHour
    }

    def minute: Int = mins
    def minute_=(newMinute: Int): Unit = {
        require(newMinute >= 0 && newMinute < 60, s"newMinute = $newMinute")
        mins = newMinute
    }

    private def timeInMinutes: Int = hour * 60 + minute

    def before(other: Time2a): Boolean = timeInMinutes < other.timeInMinutes
}



// zadanie 2b

class Time2b(_hour: Int, _minute: Int) {
    private[this] var minutesFromMidnight: Int = _
    hour = _hour
    minute = _minute

    def hour: Int = minutesFromMidnight / 60
    def hour_=(newHour: Int): Unit = {
        require(newHour >= 0 && newHour < 24, s"newHour = $newHour")
        minutesFromMidnight = newHour * 60 + minute
    }

    def minute: Int = minutesFromMidnight % 60
    def minute_=(newMinute: Int): Unit = {
        require(newMinute >= 0 && newMinute < 60, s"newMinute = $newMinute")
        minutesFromMidnight = hour * 60 + newMinute
    }

    private def timeInMinutes: Int = minutesFromMidnight

    def before(other: Time2b): Boolean = timeInMinutes < other.timeInMinutes
}



// zadanie 3

class Pojazd (val manufacturer: String,
              val model: String,
              val manufYear: Int = -1,
              var licenseNumber: String = "") {
    def this (manufacturer: String, model: String, _licenseNumber: String) = {
        this (manufacturer, model, licenseNumber = _licenseNumber)
    }
}



object Main {
    def main(args: Array[String]): Unit = {
        println("======== zadanie 1 ========")

        //val test1 = Time(25) // throws exception
        val test1 = Time1(23)

        println(test1.hour)
        test1.hour = 21
        println(test1.hour)

        test1.hour = -5;
        println(test1.hour)

        //test1.hour = 24 // throws exception

        val test2 = new Time1(3)
        println(test2.hour)


        println()
        println("======== zadanie 2a ========")

        val test2a = new Time2a(12, 34)
        println(test2a.hour)
        println(test2a.minute)

        println(test2a.before(new Time2a(11, 34)))
        println(test2a.before(new Time2a(12, 33)))
        println(test2a.before(new Time2a(12, 34)))
        println(test2a.before(new Time2a(23, 25)))

        //val test2a2 = new Time(24, 15) // throws exception
        //val test2a3 = new Time(23, 60) // throws exception


        println()
        println("======== zadanie 2b ========")

        val test2b = new Time2b(12, 34)
        println(test2b.hour)
        println(test2b.minute)
        test2b.hour = 10
        println(test2b.hour)
        println(test2b.minute)
        test2b.minute = 10
        println(test2b.hour)
        println(test2b.minute)
        println()

        println(test2b.before(new Time2b(9, 34)))
        println(test2b.before(new Time2b(10, 5)))
        println(test2b.before(new Time2b(10, 10)))
        println(test2b.before(new Time2b(23, 25)))

        //val test2b2 = new Time(24, 15) // throws exception
        //val test2b3 = new Time(23, 60) // throws exception


        println()
        println("======== zadanie 3 ========")

        val test3_1 = new Pojazd("Tesla", "Model S")
        val test3_2 = new Pojazd("Tesla", "Model 3", 2021)
        val test3_3 = new Pojazd("Tesla", "Model X", "ELECTRIC")
        val test3_4 = new Pojazd("Tesla", "Model Y", 2020, "S13N1U")

        test3_1.licenseNumber = "OtherLicense"


        println()
        println("======== zadanie 4 ========")

        try {
            metoda1()
        } catch {
            case e: Throwable => {
                println(e.getMessage + "\n")
                e.printStackTrace()
            }
        }
    }



    // zadanie 4

    def metoda1(): Unit = metoda2()

    def metoda2(): Unit = metoda3()

    def metoda3(): Unit = throw new Exception("Wyjatek zgloszony w metoda 3")
}
