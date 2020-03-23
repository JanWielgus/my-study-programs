package MainPackage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static MainPackage.Solution.CLOSING;
import static MainPackage.Solution.OPENING;

public class SolutionTest
{
    String testString;
    Solution testedSolution;

    @BeforeEach
    void setup()
    {
        testString = null;
        testedSolution = new Solution();
    }

    @Test
    void isNestedTest1()
    {
        testString = "{[()()]}";
        Assertions.assertEquals(1, testedSolution.solution(testString));
    }

    @Test
    void isNestedTest2()
    {
        testString = "{[()()][]}";
        Assertions.assertEquals(1, testedSolution.solution(testString));
    }

    @Test
    void isNestedTest3()
    {
        testString = "{}{()()}[()()]{[][()()]}()";
        Assertions.assertEquals(1, testedSolution.solution(testString));
    }

    @Test
    void isNotNestedTest1()
    {
        testString = "([)()]";
        Assertions.assertEquals(0, testedSolution.solution(testString));
    }

    @Test
    void isNotNestedTest2()
    {
        testString = "{[()()][]}}";
        Assertions.assertEquals(0, testedSolution.solution(testString));
    }

    @Test
    void isNotNestedTest3()
    {
        testString = "{}{()()}[()()]{[][()()]()";
        Assertions.assertEquals(0, testedSolution.solution(testString));
    }


    // Test of help methods

    @Test
    void checkTypeMethodTest()
    {
        Assertions.assertEquals(OPENING, testedSolution.checkType('('));
        Assertions.assertEquals(CLOSING, testedSolution.checkType(']'));
    }

    @Test
    void compareTwoBracketsTest()
    {
        Assertions.assertEquals(true, testedSolution.compareTwoBrackets('{', '}'));
        Assertions.assertEquals(true, testedSolution.compareTwoBrackets('(', ')'));
        Assertions.assertEquals(false, testedSolution.compareTwoBrackets('{', ')'));
        Assertions.assertEquals(false, testedSolution.compareTwoBrackets('[', ')'));
    }

    @Test
    void bracketIndexTest()
    {
        Assertions.assertEquals(2, testedSolution.bracketIndex('('));
        Assertions.assertEquals(2, testedSolution.bracketIndex(')'));
        Assertions.assertEquals(0, testedSolution.bracketIndex('}'));
    }
}
