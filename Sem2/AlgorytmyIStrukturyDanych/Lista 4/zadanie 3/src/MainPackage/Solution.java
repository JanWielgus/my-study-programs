package MainPackage;

/*
https://app.codility.com/programmers/lessons/16-greedy_algorithms/max_nonoverlapping_segments/
 */

public class Solution
{
    public int solution(int[] A, int[] B)
    {
        if (A.length == 0 || B.length == 0)
            return 0;

        int size = 1;
        int currentEnd = B[0];

        for (int i=1; i<A.length; i++)
        {
            // if current is overlapping, just skip it
            if (A[i] <= currentEnd)
                continue;

            // If next has the same len and is shortest, skip the current one
            int next = i + 1;
            if (next < A.length &&
                    A[i] == A[next] &&
                    (B[next] - A[next] < B[i] - A[i]))
                continue;

            currentEnd = B[i];
            size++;
        }

        return size;
    }
}
