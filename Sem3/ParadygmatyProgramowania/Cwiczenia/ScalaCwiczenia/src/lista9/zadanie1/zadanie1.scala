package lista9.zadanie1

// Jan Wielgus


class Time(_hour: Int)
{
    private[this] var h: Int = _
    def hour: Int = h
    def hour_=(newHour: Int): Unit = {
        require(newHour < 24, s"newHour = $newHour")
        if (newHour < 0) h = 0
        else h = newHour
    }

    hour = _hour
}

object Time
{
    def apply(h: Int): Time = new Time(h)
}



object Main {
    def main(args: Array[String]): Unit = {
        //val test1 = Time(25) // throws exception
        val test1 = Time(23)

        println(test1.hour)
        test1.hour = 21
        println(test1.hour)

        test1.hour = -5;
        println(test1.hour)

        //test1.hour = 24 // throws exception

        val test2 = new Time(3)
        println(test2.hour)
    }
}
