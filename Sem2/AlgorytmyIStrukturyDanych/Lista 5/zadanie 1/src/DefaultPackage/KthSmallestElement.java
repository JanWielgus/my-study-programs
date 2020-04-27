package DefaultPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KthSmallestElement
{
    // Return Kth smallest element in the ArrayList
    public static <T extends Comparable<T>> T getKthSmElem(int k, List<T> list)
    {
        return getKthSmElem(k, list, 0, list.size()-1);
    }


    public static <T extends Comparable<T>> T getKthSmElem(int k, List<T> list, int leftID, int rightID)
    {
        // Check illegal states
        if (list == null)
            throw new NullPointerException("Passed list is a null");
        if (list.size() == 0)
            throw new IllegalArgumentException("Passed array has no elements");
        if (leftID > rightID)
            throw new IllegalArgumentException("left index is bigger than right index");
        if (k <= 0 || k > (rightID - leftID + 1))
            throw new IndexOutOfBoundsException(k + " element is out of the passed list bounds");

        // if the list has one element, return it
        if (list.size() == 1)
            return list.get(0);

        // Divide the array into the three subarrays
        ArrayList<T> subarrayLeft = new ArrayList<>(); // less than random element
        ArrayList<T> subarrayMiddle = new ArrayList<>(); // equal to random element
        ArrayList<T> subarrayRight = new ArrayList<>(); // bigger than random element

        Random rand = new Random();
        T uElem = list.get(rand.nextInt(rightID - leftID + 1));

        //for (T elem: list)
        for (int i=leftID; i<= rightID; i++)
        {
            int result = list.get(i).compareTo(uElem);
            if (result < 0)
                subarrayLeft.add(list.get(i));
            else if (result == 0)
                subarrayMiddle.add(list.get(i));
            else // result > 0
                subarrayRight.add(list.get(i));
        }

        // Recursive calls
        if (k <= subarrayLeft.size())
            return getKthSmElem(k, subarrayLeft);
        else if (k <= subarrayLeft.size() + subarrayMiddle.size())
            return uElem;
        else
            return getKthSmElem(k - subarrayLeft.size() - subarrayMiddle.size(), subarrayRight);
    }
}
