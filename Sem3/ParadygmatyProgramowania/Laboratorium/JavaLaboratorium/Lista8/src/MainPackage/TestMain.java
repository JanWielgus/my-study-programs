package MainPackage;

// Jan Wielgus

public class TestMain
{
    public static void main(String[] args)
    {
        Zwierz[] animalArray = new Zwierz[5];

        animalArray[0] = new Wrona();
        animalArray[1] = new Pies("Azor");
        animalArray[2] = new Ptak();
        animalArray[3] = new Ptak("Centek");
        animalArray[4] = new Wrona("Kraka");

        for (Zwierz zwierz : animalArray)
        {
            System.out.println(zwierz.toString());
        }

        System.out.printf("koniec");
    }
}
