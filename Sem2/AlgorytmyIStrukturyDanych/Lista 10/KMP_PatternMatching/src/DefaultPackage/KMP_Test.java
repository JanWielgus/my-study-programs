package DefaultPackage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class KMP_Test
{
    @BeforeEach
    void beforeEach()
    {

    }


    @Test
    void test1()
    {
        String text = "AABAACAADAABAABA";
        String pattern = "AABA";

        List<Integer> expectedList = new ArrayList<>();
        expectedList.add(0);
        expectedList.add(9);
        expectedList.add(12);

        List<Integer> actualList = KMP_PatternMatching.search(text, pattern);

        Assertions.assertArrayEquals(expectedList.toArray(), actualList.toArray());
    }


    @Test
    void test2()
    {
        String text = "sfdhfasdgjjjdklsdlkjjdkaskdgklsjdfjjdk";
        String pattern = "jjdk";



        List<Integer> expectedList = new ArrayList<>();
        expectedList.add(10);
        expectedList.add(19);
        expectedList.add(34);

        List<Integer> actualList = KMP_PatternMatching.search(text, pattern);

        Assertions.assertArrayEquals(expectedList.toArray(), actualList.toArray());
    }



    @Test
    void test3()
    {
        String text = "Kropiciel, stanowisko zająwszy w oborze,\n" +
                "Jednego wołu i dwa cielce w łby zakropił,\n" +
                "A Brzytewka im szable w gardzielach utopił;\n" +
                "Szydełko równie czynnie używał swej szpadki,\n" +
                "Kabany i prosięta koląc pod łopatki.";
        String pattern = "gardzielach";



        List<Integer> expectedList = new ArrayList<>();
        expectedList.add(107);

        List<Integer> actualList = KMP_PatternMatching.search(text, pattern);

        Assertions.assertArrayEquals(expectedList.toArray(), actualList.toArray());
    }
}
