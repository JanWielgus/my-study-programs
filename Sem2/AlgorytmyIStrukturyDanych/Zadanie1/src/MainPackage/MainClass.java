package MainPackage;
import MyListPackage.*;

public class MainClass
{
    public static void main(String[] args)
    {
        System.out.println("Tworze tablice uzywajac iteratora");

        MyList<Integer> testList = new MyList<>();

        testList.add(5);
        for (int i=0; i<15; i++)
            testList.add(i*3);

        showList(testList);

        System.out.println("Usuwam element z indeksem 1");
        testList.remove(1);
        showList(testList);

        System.out.println("Zmieniam 14 element na -1");
        testList.set(14, -1);
        showList(testList);

        System.out.println("Dodaję element 100 na pozycje 4");
        testList.add(4, 100);
        showList(testList);

        System.out.println("Czy tablica zawiera element 12?");
        if (testList.contains(12))
            System.out.println("Tak, indeks to " + testList.indexOf(12));
        else
            System.out.println("Nie");
        System.out.println(); // new line

        System.out.println("Czy tablica zawiera element 41?");
        if (testList.contains(41))
            System.out.println("Tak, indeks to " + testList.indexOf(41));
        else
            System.out.println("Nie");
        System.out.println(); // new line

        System.out.println("Rozmiar tablicy: " + testList.size());
        System.out.println(); // new line

        System.out.println("Czyszcze tablice");
        testList.clear();
        showList(testList);

        System.out.println("Dodaje dwa nowe elementy");
        testList.add(8);
        testList.add(10);
        showList(testList);
    }


    public static <T> void showList(MyList<T> list)
    {
        System.out.println("Zawartosc listy:");
        int i=0;
        for (T obj: list)
            System.out.println((i++) + ": " + obj);
        System.out.println("end");
        System.out.println();
    }
}
