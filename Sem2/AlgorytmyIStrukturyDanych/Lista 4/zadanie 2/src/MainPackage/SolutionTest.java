package MainPackage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SolutionTest
{

    @Test
    void test1()
    {
        Solution sol = new Solution();
        int[] A = new int[7];
        A[0] = 1;
        A[1] = 2;
        A[2] = 3;
        A[3] = 4;
        A[4] = 1;
        A[5] = 1;
        A[6] = 3;

        Assertions.assertEquals(3, sol.solution(4, A));
    }
}
