package DefaultPackage;

public class Solution
{
    public int solution(int[] A)
    {
        int maxAbs = 0;

        // make abs of all elements and find its maximum value
        for (int i=0; i<A.length; i++)
        {
            A[i] = Math.abs(A[i]);
            maxAbs = Math.max(A[i], maxAbs);
        }

        // calculate sum of all elements (of its abs value)
        int sum = ArraySum(A);

        // create array and fill it with zeros
        int[] count = new int[maxAbs + 1];
        for (int i=0; i<count.length; i++)
            count[i] = 0;

        // count all elements
        for (int elem: A)
            count[elem]++;

        // make array of multipliers (first is 0 and remaining are -1)
        int[] multipliers = new int[sum + 1];
        multipliers[0] = 0;
        for (int i=1; i<multipliers.length; i++)
            multipliers[i] = -1;


        for (int i=1; i < maxAbs+1; i++)
        {
            if (count[i] > 0)
            {
                for (int j=0; j < sum; j++)
                {
                    if (multipliers[j] >= 0)
                        multipliers[j] = count[i];
                    else if (j >= i && multipliers[j-i] > 0)
                        multipliers[j] = multipliers[j-i] - 1;
                }
            }
        }

        int result = sum;

        for (int i=0; i < (sum/2 + 1); i++)
        {
            if (multipliers[i] >= 0)
                result = Math.min(result, sum - 2 * i);
        }

        return result;
    }


    private int ArraySum(int[] array)
    {
        int sum = 0;
        for (int i=0; i<array.length; i++)
            sum += array[i];
        return sum;
    }
}
