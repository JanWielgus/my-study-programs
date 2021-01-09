package lista9.zadanie2b

// Jan Wielgus


class Time (_hour: Int, _minute: Int) {
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

    def before(other: Time): Boolean = timeInMinutes < other.timeInMinutes
}


object Main {
    def main(args: Array[String]): Unit = {
        val test1 = new Time(12, 34)
        println(test1.hour)
        println(test1.minute)
        test1.hour = 10
        println(test1.hour)
        println(test1.minute)
        test1.minute = 10
        println(test1.hour)
        println(test1.minute)
        println()

        println(test1.before(new Time(9, 34)))
        println(test1.before(new Time(10, 5)))
        println(test1.before(new Time(10, 10)))
        println(test1.before(new Time(23, 25)))

        //val test2 = new Time(24, 15) // throws exception
        //val test3 = new Time(23, 60) // throws exception
    }
}
