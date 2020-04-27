package DefaultPackage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class MySortAlgsTest
{
    ArrayList<Integer> testList;

    @BeforeEach
    void beforeEach()
    {
        testList = new ArrayList<>();

        // put some initial elements
        testList.add(10);
        testList.add(6);
        testList.add(5);
        testList.add(1);
        testList.add(11);
    }


    @Test
    void quickSortTest1()
    {
        testList.add(100);
        testList.add(50);
        testList.add(10001);
        testList.add(5);

        MySortAlgs.quickSort(testList);
        Assertions.assertArrayEquals(testList.toArray() , new Object[]{1, 5, 5, 6, 10, 11, 50, 100, 10001});

        // sort sorted array
        MySortAlgs.quickSort(testList);
        Assertions.assertArrayEquals(testList.toArray() , new Object[]{1, 5, 5, 6, 10, 11, 50, 100, 10001});
    }

    @Test
    void quickSortTest2PartialSort()
    {
        testList.add(100);
        testList.add(50);
        testList.add(10001);
        testList.add(5);

        MySortAlgs.quickSort(testList, 0, 4);
        Assertions.assertArrayEquals(new Object[]{1, 5, 6, 10, 11, 100, 50, 10001, 5}, testList.toArray());

        // sort second half
        MySortAlgs.quickSort(testList, 5, 8);
        Assertions.assertArrayEquals(new Object[]{1, 5, 6, 10, 11, 5, 50, 100, 10001}, testList.toArray());

        // sort whole array
        MySortAlgs.quickSort(testList, 0, 8);
        Assertions.assertArrayEquals(new Object[]{1, 5, 5, 6, 10, 11, 50, 100, 10001}, testList.toArray());
    }

    @Test
    void quickSortTest3()
    {
        testList.add(2);
        testList.add(1);
        testList.add(3);
        testList.add(-5);
        testList.add(100);
        testList.add(98);

        MySortAlgs.quickSort(testList);
        Assertions.assertArrayEquals(new Object[]{-5, 1, 1, 2, 3, 5, 6, 10, 11, 98, 100}, testList.toArray());
    }

    @Test
    void quickSortTest4Exceptions()
    {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            MySortAlgs.quickSort(testList, 0, 5);
        });

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            MySortAlgs.quickSort(testList, -1, 4);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            MySortAlgs.quickSort(testList, 3, 2);
        });
    }


    @Test
    void insertionSortTest1()
    {
        testList.add(2);
        testList.add(1);
        testList.add(3);
        testList.add(-5);
        testList.add(100);
        testList.add(98);

        MySortAlgs.insertionSort(testList);
        Assertions.assertArrayEquals(new Object[]{-5, 1, 1, 2, 3, 5, 6, 10, 11, 98, 100}, testList.toArray());
    }


    @Test
    void bucketSortTest1()
    {
        testList.add(100);
        testList.add(50);
        testList.add(10001);
        testList.add(5);

        MySortAlgs.bucketSort(testList);
        Assertions.assertArrayEquals(testList.toArray() , new Object[]{1, 5, 5, 6, 10, 11, 50, 100, 10001});

        // sort sorted array
        MySortAlgs.bucketSort(testList);
        Assertions.assertArrayEquals(testList.toArray() , new Object[]{1, 5, 5, 6, 10, 11, 50, 100, 10001});
    }


    @Test
    void bucketSortTest2()
    {
        testList.add(2);
        testList.add(7);
        testList.add(4);
        testList.add(1);
        testList.add(9);

        MySortAlgs.bucketSort(testList);
        Assertions.assertArrayEquals(new Object[]{1, 1, 2, 4, 5, 6, 7, 9, 10 ,11}, testList.toArray());
    }


    @Test
    void bucketSortTest3()
    {
        testList.add(2);
        testList.add(1);
        testList.add(3);
        testList.add(-5);
        testList.add(100);
        testList.add(98);

        MySortAlgs.bucketSort(testList);
        Assertions.assertArrayEquals(new Object[]{-5, 1, 1, 2, 3, 5, 6, 10, 11, 98, 100}, testList.toArray());
    }
}
