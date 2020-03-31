package MainPackage;

import java.util.ArrayList;

public class Solution
{
    public int solution(int K, int[] A)
    {
        // Put everything inside the ArrayList to enable removing elements
        ArrayList<Integer> arr = new ArrayList<>();
        for (int temp: A)
            arr.add(temp);

        int maxAmtOfRopes = 0;

        while(findAndRemoveNextRopes(K, arr))
        {
            maxAmtOfRopes++;
        }

        return maxAmtOfRopes;
    }

    private boolean findAndRemoveNextRopes(int K, ArrayList<Integer> arr)
    {
        int i=0;
        int currentRopeLength = 0;

        // Tie the minimum amt of ropes to be higher than K
        while (currentRopeLength <= K)
        {
            // Return false if rope is not long enough
            // But there are no ropes available
            if (arr.size() == 0)
                return false;

            // Tie down the longest rope available
            int curID = indexOfTheBiggestValue(arr);
            currentRopeLength += arr.get(curID);
            // And remove it from the available ropes array
            arr.remove(curID);
        }

        return true;
    }

    private int indexOfTheBiggestValue(ArrayList<Integer> A)
    {
        int biggest = A.get(0);
        int biggestID = 0;

        for (int i=1; i<A.size(); i++)
        {
            if (A.get(0) > biggest)
            {
                biggest = A.get(0);
                biggestID = i;
            }
        }

        return biggestID;
    }

}
