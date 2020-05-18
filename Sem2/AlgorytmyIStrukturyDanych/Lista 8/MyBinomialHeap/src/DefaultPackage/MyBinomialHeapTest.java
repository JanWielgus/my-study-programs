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
}
