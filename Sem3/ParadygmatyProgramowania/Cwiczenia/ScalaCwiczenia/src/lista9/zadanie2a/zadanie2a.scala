package lista9.zadanie2a

// Jan Wielgus


class Time (_hour: Int, _minute: Int) {
    private[this] var hrs: Int = _
    def hour: Int = hrs
    def hour_=(newHour: Int): Unit = {
        require(newHour >= 0 && newHour < 24, s"newHour = $newHour")
        hrs = newHour
    }

    private[this] var mins: Int = _
    def minute: Int = mins
    def minute_=(newMinute: Int): Unit = {
        require(newMinute >= 0 && newMinute < 60, s"newMinute = $newMinute")
        mins = newMinute
    }

    hour = _hour
    minute = _minute

    def before(other: Time): Boolean = {
        if (hour < other.hour) true
        else if (hour == other.hour && minute < other.minute) true
        else false
    }
}


object Main {
    def main(args: Array[String]): Unit = {
        val test1 = new Time(12, 34)
        println(test1.hour)
        println(test1.minute)

        println(test1.before(new Time(11, 34)))
        println(test1.before(new Time(12, 33)))
        println(test1.before(new Time(12, 34)))
        println(test1.before(new Time(23, 25)))

        //val test2 = new Time(24, 15) // throws exception
        //val test3 = new Time(23, 60) // throws exception
    }
}
