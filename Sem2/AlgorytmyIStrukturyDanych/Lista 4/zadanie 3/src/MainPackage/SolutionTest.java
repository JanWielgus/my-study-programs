package MainPackage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SolutionTest
{
    @Test
    void test1()
    {
        Solution sol = new Solution();
        int[] A = new int[5];
        int[] B = new int[5];

        A[0] = 1;
        A[1] = 3;
        A[2] = 7;
        A[3] = 9;
        A[4] = 9;

        B[0] = 5;
        B[1] = 6;
        B[2] = 8;
        B[3] = 9;
        B[4] = 10;

        Assertions.assertEquals(3, sol.solution(A, B));
    }
}
