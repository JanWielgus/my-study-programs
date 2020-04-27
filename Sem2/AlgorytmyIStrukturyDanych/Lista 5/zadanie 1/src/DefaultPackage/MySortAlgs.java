package DefaultPackage;

import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MySortAlgs
{
    public static <T extends Comparable<T>> void quickSort(List<T> list)
    {
        quickSort(list, 0, list.size()-1);
    }

    public static <T extends Comparable<T>> void quickSort(List<T> list, int leftID, int rightID)
    {
        if (list == null)
            throw new NullPointerException("Passed list is not instantiated");

        // Check index out of bounds exception
        if (leftID < 0 || rightID >= list.size())
            throw new IndexOutOfBoundsException("Left of right list index is out of bounds");

        // Check if left ID is less or equal to right ID
        if (leftID > rightID)
            throw new IllegalArgumentException("left index is bigger than right index");

        // If provided list has one or zero elements, it is sorted
        if (list.size() < 2)
            return;

        // Choose pivot using Median of medians algorithm (algorytm magicznych piatek)
        int middleID = (rightID - leftID + 1) / 2; // amountOfElements / 2
        T pivot = KthSmallestElement.getKthSmElem(middleID+1, list, leftID, rightID);

        // indexes that are approaching from left and right
        // partitioning algorithm
        int apprLeft = leftID;
        int apprRight = rightID;

        // Move all elements smaller or equal to pivot to the left
        // And all elements bigger than pivot to the right
        do {
            // Find the first element bigger than pivot from left
            while (list.get(apprLeft).compareTo(pivot) < 0 && apprLeft+1 <= rightID)
                apprLeft++;

            // Find the first element smaller than pivot from right
            while (list.get(apprRight).compareTo(pivot) > 0 && apprRight-1 >= leftID)
                apprRight--;

            if (apprLeft <= apprRight)
            {
                // swap elements
                T temp = list.get(apprLeft);
                list.set(apprLeft, list.get(apprRight));
                list.set(apprRight, temp);

                apprLeft++;
                apprRight--;
            }
        } while (apprLeft <= apprRight);

        // recursively sort divided array
        if (apprRight > leftID)
            quickSort(list, leftID, apprRight);
        if (apprLeft < rightID)
            quickSort(list, apprLeft, rightID);
    }



    public static <T extends Number & Comparable<T>> void bucketSort(List<T> list)
    {
        if (list == null)
            throw new NullPointerException("Passed list is not instantiated");

        if (list.size() < 2)
            return;

        // Create buckets
        int amtOfBuckets = list.size();
        Bucket<T>[] bucketArrays = new Bucket[amtOfBuckets];
        for (int i=0; i<amtOfBuckets; i++)
            bucketArrays[i] = new Bucket<>();

        T smallest = getMin(list);
        T biggest = getMax(list);


        // put data into buckets
        for (T element: list)
        {
            int j = (int)(map(element.doubleValue(), smallest.doubleValue(), biggest.doubleValue(),
                    0, amtOfBuckets-1) + 0.5);
            //System.out.println("J: " + j);
            bucketArrays[j].addElement(element);
        }

        /*
        // Test - show all buckets
        for (Bucket<T> bucket: bucketArrays)
        {
            System.out.print("Bucket:");
            for (int i=0; i<bucket.getList().size(); i++)
                System.out.print(" " + bucket.getList().get(i));
            System.out.println();
        }*/

        // sort buckets and add update result array
        int currentAppendIndex = 0;
        for (Bucket<T> bucket: bucketArrays)
        {
            List<T> bucketList = bucket.getList();
            if (bucketList.size() > 1)
                insertionSort(bucketList);

            for (int i=0; i<bucketList.size(); i++)
            {
                list.set(currentAppendIndex, bucketList.get(i));
                currentAppendIndex++; // increase append index
            }
        }
    }



    private static class Bucket<T extends Number>
    {
        private List<T> list;

        public Bucket()
        {
            list = new ArrayList<>();
        }

        public void addElement(T element)
        {
            list.add(element);
        }

        public List<T> getList()
        {
            return list;
        }
    }

    public static <T extends Comparable<T>> void insertionSort(List<T> list)
    {
        for(int i=0; i < list.size(); i++)
        {
            int j = i;
            while (j > 0 && list.get(j-1).compareTo(list.get(j)) > 0)
            {
                T key = list.get(j);
                list.set(j, list.get(j-1));
                list.set(j-1, key);
                j--;
            }
        }
    }


    private static int compareNumbers(Number n1, Number n2)
    {
        long l1 = n1.longValue();
        long l2 = n2.longValue();
        if (l1 != l2)
            return (l1 < l2 ? -1 : 1);
        return Double.compare(n1.doubleValue(), n2.doubleValue());
    }


    private static <T extends Number& Comparable<T>> T getMin(List<T> numbers)
    {
        T minimum = numbers.get(0);
        for (int i=1; i<numbers.size(); i++)
            if (numbers.get(i).compareTo(minimum) < 0)
                minimum = numbers.get(i);
        return minimum;
    }


    private static <T extends Number& Comparable<T>> T getMax(List<T> numbers)
    {
        T maximum = numbers.get(0);
        for (int i=1; i<numbers.size(); i++)
            if (numbers.get(i).compareTo(maximum) > 0)
                maximum = numbers.get(i);
        return maximum;
    }


    private static double map(double x, double in_min, double in_max, double out_min, double out_max)
    {
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }
}
