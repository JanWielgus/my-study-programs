package MainPackage;

import java.util.ArrayList;
import java.util.Random;

public class KthSmallestElement
{
    // Return Kth smallest element in the ArrayList
    public static <T extends Comparable<T>> T getKthSmElem(int k, ArrayList<T> list)
    {
        // Check illegal states
        if (list == null)
            throw new NullPointerException("Passed list is a null");
        if (list.size() == 0)
            throw new IllegalArgumentException("Passed array has no elements");
        if (k <= 0 || k > list.size())
            throw new IndexOutOfBoundsException(k + " element is out of the passed list bounds");

        // if the list has one element, return it
        if (list.size() == 1)
            return list.get(0);

        // Divide the array into the three subarrays
        ArrayList<T> Sl = new ArrayList<>(); // less than random element
        ArrayList<T> Su = new ArrayList<>(); // equal to random element
        ArrayList<T> Sr = new ArrayList<>(); // bigger than random element

        Random rand = new Random();
        T uElem = list.get(rand.nextInt(list.size()-1));

        for (T elem: list)
        {
            int result = elem.compareTo(uElem);
            if (result < 0)
                Sl.add(elem);
            else if (result == 0)
                Su.add(elem);
            else // result > 0
                Sr.add(elem);
        }


        // Recursive calls
        if (k <= Sl.size())
            return getKthSmElem(k, Sl);
        else if (k <= Sl.size() + Su.size())
            return uElem;
        else
            return getKthSmElem(k - Sl.size() - Su.size(), Sr);
    }
}
