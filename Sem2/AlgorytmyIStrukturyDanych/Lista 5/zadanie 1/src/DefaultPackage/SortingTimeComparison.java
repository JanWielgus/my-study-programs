package DefaultPackage;

import java.lang.reflect.Array;
import java.util.*;

public class SortingTimeComparison
{
    public static void main(String[] args)
    {
        int arraySize = 1000000;

        Random rand = new Random();
        ArrayList<Integer> integerList = new ArrayList<>();
        for (int i=0; i<arraySize; i++)
            integerList.add(rand.nextInt());

        List<Integer> toSort;


        // Quick sort
        System.out.println("Quick sort: ");
        toSort = (List<Integer>)integerList.clone();
        TimeMeasurement.start();
        MySortAlgs.quickSort(toSort);
        TimeMeasurement.end();
        System.out.println("Duration: " + TimeMeasurement.getDurationInSeconds());
        System.out.println();


        // Bucket sort
        System.out.println("Bucket sort: ");
        toSort = (List<Integer>)integerList.clone();
        TimeMeasurement.start();
        MySortAlgs.bucketSort(toSort);
        TimeMeasurement.end();
        System.out.println("Duration: " + TimeMeasurement.getDurationInSeconds());
        System.out.println();


        // Arrays.sort
        System.out.println("Arrays.sort: ");
        toSort = (List<Integer>)integerList.clone();
        Object[] arrayToSort = toSort.toArray();
        TimeMeasurement.start();
        Arrays.sort(arrayToSort);
        TimeMeasurement.end();
        System.out.println("Duration: " + TimeMeasurement.getDurationInSeconds());
        System.out.println();


        // Collections.sort
        System.out.println("Collections.sort: ");
        toSort = (List<Integer>)integerList.clone();
        TimeMeasurement.start();
        Collections.sort(toSort);
        TimeMeasurement.end();
        System.out.println("Duration: " + TimeMeasurement.getDurationInSeconds());
        System.out.println();
    }


    private static <T> void showList(List<T> list)
    {
        System.out.println("List size " + list.size());
        System.out.println(list);
        System.out.println();
    }

}


class TimeMeasurement
{
    private static long startTime; // ns
    private static long duration; // ns

    public static void start() {
        startTime = System.nanoTime();
    }

    public static void end() {
        duration = System.nanoTime() - startTime;
    }

    public static double getDurationInSeconds() {
        return (double) duration / 1000000000.0;
    }
}
