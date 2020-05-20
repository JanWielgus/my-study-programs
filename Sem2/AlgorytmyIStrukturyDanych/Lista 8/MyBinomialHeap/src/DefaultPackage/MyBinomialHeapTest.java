package DefaultPackage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MyBinomialHeapTest
{
    private MyBinomialHeap<Integer> testHeap;

    @BeforeEach
    void beforeEach()
    {
        testHeap = new MyBinomialHeap<>();
    }


    @Test
    void minimumElementTest()
    {
        testHeap.insert(10);
        testHeap.insert(5);
        testHeap.insert(4);

        Assertions.assertEquals(4, testHeap.minimum());

        testHeap.insert(15);
        testHeap.insert(50);
        testHeap.insert(13);
        testHeap.insert(3);
        testHeap.insert(8);
        testHeap.insert(14);

        Assertions.assertEquals(3, testHeap.minimum());
    }


    @Test
    void deleteTest()
    {
        testHeap.insert(100);
        testHeap.insert(50);
        testHeap.insert(14);
        testHeap.insert(124);
        testHeap.insert(5);
        testHeap.insert(73);

        Assertions.assertEquals(5, testHeap.minimum());

        testHeap.delete(50);
        Assertions.assertEquals("  5  73  124  14  100", testHeap.toString());
        Assertions.assertEquals(5, testHeap.minimum());


        testHeap.delete(124);
        Assertions.assertEquals("  5  73  14  100", testHeap.toString());
        Assertions.assertEquals(5, testHeap.minimum());


        testHeap.delete(5);
        Assertions.assertEquals("  73  14  100", testHeap.toString());
        Assertions.assertEquals(14, testHeap.minimum());


        testHeap.delete(100);
        Assertions.assertEquals("  14  73", testHeap.toString());
        Assertions.assertEquals(14, testHeap.minimum());


        testHeap.delete(14);
        Assertions.assertEquals("  73", testHeap.toString());
        Assertions.assertEquals(73, testHeap.minimum());


        testHeap.delete(73);
        Assertions.assertThrows(IllegalStateException.class, () ->{
            testHeap.minimum();
        });


        testHeap.insert(123);
        Assertions.assertEquals(123, testHeap.minimum());
    }



    @Test
    void extractMinimumTest()
    {
        testHeap.insert(21);
        testHeap.insert(10);
        testHeap.insert(14);
        testHeap.insert(13);
        testHeap.insert(5);
        testHeap.insert(3);

        Assertions.assertEquals(3, testHeap.extractMin());
        Assertions.assertEquals(5, testHeap.extractMin());
        Assertions.assertEquals(10, testHeap.extractMin());
        Assertions.assertEquals(13, testHeap.extractMin());
        Assertions.assertEquals(14, testHeap.extractMin());
    }


    @Test
    void unionTest()
    {
        testHeap.insert(21);
        testHeap.insert(10);
        testHeap.insert(14);
        testHeap.insert(13);
        testHeap.insert(5);
        testHeap.insert(3);

        MyBinomialHeap<Integer> secondHeap = new MyBinomialHeap<>();
        secondHeap.insert(100);
        secondHeap.insert(50);
        secondHeap.insert(14);
        secondHeap.insert(124);
        secondHeap.insert(5);
        secondHeap.insert(73);

        testHeap.union(secondHeap);

        Assertions.assertEquals("  3  5  10  21  13  14  5  73  14  124  50  100", testHeap.toString());
        Assertions.assertEquals(3, testHeap.extractMin());
        Assertions.assertEquals(5, testHeap.extractMin());
        Assertions.assertEquals(5, testHeap.extractMin());
        Assertions.assertEquals(10, testHeap.extractMin());
    }


    @Test
    void decreaseKeyTest()
    {
        testHeap.insert(21);
        testHeap.insert(10);
        testHeap.insert(14);
        testHeap.insert(13);
        testHeap.insert(5);
        testHeap.insert(3);

        testHeap.decreaseKey(21, 1);
        Assertions.assertEquals(1, testHeap.extractMin());

        testHeap.decreaseKey(5, 1);
        Assertions.assertEquals(1, testHeap.extractMin());
        Assertions.assertEquals(3, testHeap.extractMin());
    }
}
