package lista9.zadanie3

// Jan Wielgus


class Pojazd (val manufacturer: String,
              val model: String,
              val manufYear: Int = -1,
              var licenseNumber: String = "") {
    def this (manufacturer: String, model: String, _licenseNumber: String) = {
        this (manufacturer, model, licenseNumber = _licenseNumber)
    }
}


object Main {
    def main(args: Array[String]): Unit =
    {
        val test1 = new Pojazd("Tesla", "Model S")
        val test2 = new Pojazd("Tesla", "Model 3", 2021)
        val test3 = new Pojazd("Tesla", "Model X", "ELECTRIC")
        val test4 = new Pojazd("Tesla", "Model Y", 2020, "S13N1U")

        test1.licenseNumber = "OtherLicense"
    }
}
