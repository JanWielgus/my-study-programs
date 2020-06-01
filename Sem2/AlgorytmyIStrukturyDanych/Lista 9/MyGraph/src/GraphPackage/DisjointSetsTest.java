package GraphPackage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DisjointSetsTest
{
    private DisjointSets<Integer> testDisSet;

    @BeforeEach
    void beforeEach()
    {
        testDisSet = new DisjointSets();

        testDisSet.makeSet(5);
        testDisSet.makeSet(10);
        testDisSet.makeSet(15);
    }


    @Test
    void simpleTest1()
    {
        testDisSet.makeSet(7);
        testDisSet.makeSet(50);
        testDisSet.makeSet(1);

        testDisSet.union(7, 5);
        testDisSet.union(15, 50);
        testDisSet.union(5, 10);

        Assertions.assertEquals(3, testDisSet.getDisjointSets().size());

        testDisSet.union(7, 1);
        Assertions.assertEquals(2, testDisSet.getDisjointSets().size());

        testDisSet.union(50, 1);
        Assertions.assertEquals(1, testDisSet.getDisjointSets().size());
        Assertions.assertEquals(6, testDisSet.getDisjointSets().get(0).size());
    }


}
