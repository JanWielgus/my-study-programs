package MainPackage;

/*

https://app.codility.com/programmers/lessons/16-greedy_algorithms/tie_ropes/

 */

public class Solution
{
    public int solution(int K, int[] A)
    {
        int maxAmtOfRopes = 0; // result
        int currentLength = 0; // current length of tied ropes (reset when greater than K)

        for (int i=0; i<A.length; i++)
        {
            currentLength += A[i];

            if (currentLength >= K)
            {
                maxAmtOfRopes++;
                currentLength = 0;
            }
        }

        return maxAmtOfRopes;
    }

}
