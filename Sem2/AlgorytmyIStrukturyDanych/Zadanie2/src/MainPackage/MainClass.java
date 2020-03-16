package MainPackage;

import ClassFromLecture.IList;
import MyClassPackage.ExtendedOneWayLinkedListWithHead;

import java.util.ListIterator;

public class MainClass
{
    public static void main(String[] args)
    {
        System.out.println("Dziala");

/*
        ExtendedOneWayLinkedListWithHead<Integer> testList2 = new ExtendedOneWayLinkedListWithHead<>();

        testList2.add(1);
        testList2.add(2);
        testList2.add(3);

        ListIterator<Integer> testIterator = testList2.listIterator();
        testIterator.next();
        System.out.println("Next " + testIterator.next());
        testIterator.add(5);
        System.out.println("Next " + testIterator.next());

        showList(testList2);

        testList2.remove(3);
        showList(testList2);


*/


        // Other tests

        ExtendedOneWayLinkedListWithHead<Integer> testList = new ExtendedOneWayLinkedListWithHead<>();

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

    public static <T> void showList (IList<T> toShow)
    {
        System.out.println("Wyswietlam liste o dlugosci " + toShow.size());
        int currentIndex = 0;

        for (T element: toShow)
        {
            System.out.println((currentIndex++) + ". " + element);
        }

        System.out.println("KONIEC");
        System.out.println();
    }
}
