package MainPackage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.zip.Adler32;

public class KthSmallestElementTest
{
    ArrayList<Integer> testList;

    @BeforeEach
    void setup()
    {
        testList = new ArrayList<>();

        testList.add(3);
        testList.add(9);
        testList.add(6);
        testList.add(2);
    }


    @Test
    void generalTest1()
    {
        testList.add(1);
        testList.add(1);
        testList.add(1);
        testList.add(1);
        testList.add(1);
        testList.add(1);

        Assertions.assertEquals(1, KthSmallestElement.getKthSmElem(1, testList));
        Assertions.assertEquals(1, KthSmallestElement.getKthSmElem(6, testList));
        Assertions.assertEquals(2, KthSmallestElement.getKthSmElem(7, testList));
        Assertions.assertEquals(3, KthSmallestElement.getKthSmElem(8, testList));
        Assertions.assertEquals(6, KthSmallestElement.getKthSmElem(9, testList));
        Assertions.assertEquals(9, KthSmallestElement.getKthSmElem(10, testList));
    }


    @Test
    void generalTest2()
    {
        testList.add(1123);
        testList.add(6);
        testList.add(4);
        testList.add(3);
        testList.add(134);
        testList.add(10);

        // There are two duplicates inside the array

        Assertions.assertEquals(2, KthSmallestElement.getKthSmElem(1, testList));
        Assertions.assertEquals(3, KthSmallestElement.getKthSmElem(2, testList));
        Assertions.assertEquals(3, KthSmallestElement.getKthSmElem(3, testList));
        Assertions.assertEquals(4, KthSmallestElement.getKthSmElem(4, testList));
        Assertions.assertEquals(6, KthSmallestElement.getKthSmElem(5, testList));
        Assertions.assertEquals(6, KthSmallestElement.getKthSmElem(6, testList));
        Assertions.assertEquals(9, KthSmallestElement.getKthSmElem(7, testList));
        Assertions.assertEquals(10, KthSmallestElement.getKthSmElem(8, testList));
        Assertions.assertEquals(134, KthSmallestElement.getKthSmElem(9, testList));
        Assertions.assertEquals(1123, KthSmallestElement.getKthSmElem(10, testList));
    }


    @Test
    void indexOutOfBoundsExceptionTest()
    {
        Assertions.assertEquals(9, KthSmallestElement.getKthSmElem(4, testList));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            KthSmallestElement.getKthSmElem(5, testList);
        });

        testList.add(-5);

        Assertions.assertThrows(IndexOutOfBoundsException.class, () ->{
            KthSmallestElement.getKthSmElem(7, testList);
        });
        Assertions.assertEquals(-5, KthSmallestElement.getKthSmElem(1, testList));

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            KthSmallestElement.getKthSmElem(-3, testList);
        });
    }


    @Test
    void illegalArgumentExceptionTest()
    {
        // If passed an empty array

        testList.clear();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            KthSmallestElement.getKthSmElem(1, testList);
        });
    }


    @Test
    void nullPtrExceptionTest()
    {
        Assertions.assertThrows(NullPointerException.class, () -> {
            KthSmallestElement.getKthSmElem(1, null);
        });
    }

}



