package MyListPackage;

import javax.swing.*;
import java.util.Iterator;

public class MyList<T> implements Iterable<T>
{
    private T[] array = null;
    private int actualSize = 0;
    private final int defaultCapacity = 10;


    public MyList()
    {
        // ensure default capacity
        ensureCapacity(10);
    }


    public boolean add(int index, T element)
    {
        if (index < 0)
            return false;

        // If there are no array ensure the default capacity
        if (array == null)
            ensureCapacity(defaultCapacity);
        // Check if need to extend the array size
        else if (actualSize+1 > array.length)
        {
            if (actualSize < defaultCapacity)
                ensureCapacity(defaultCapacity);
            else
                ensureCapacity(actualSize*2); // double the previous capacity
        }

        // If index is between previous data
        if (index < actualSize)
        {
            // move all elements after index up
            for (int i=actualSize-1; i>=index; i--)
                array[i+1] = array[i];

            // put new element at the index position
            array[index] = element;

            // Increase the array size
            actualSize++;
        }
        // If index is at the end
        else if (index == actualSize)
        {
            // Put the element at the index position (end)
            array[index] = element;

            // Increase the array size
            actualSize++;
        }
        // If outside the array borders
        else
            return false;

        // If everything went well
        return true;
    }


    public boolean add(T element)
    {
        return add(actualSize, element);
    }


    public void clear()
    {
        // Reset the array state
        array = null;
        actualSize = 0;
    }


    public boolean contains(T object)
    {
        for (T obj: array)
        {
            if (obj == object)
                return true;
        }

        return false;
    }


    public void ensureCapacity(int minCapacity)
    {
        // If provided capacity is less than 1 or less than previous array size
        if (minCapacity < 1)
            return;

        if (array == null)
        {
            // create new array
            Object[] temp = new Object[minCapacity];
            array = (T[])temp;

            // Reset the actual size
            actualSize = 0;
        }
        else
        {
            // if try to change to less capacity than were before
            if (minCapacity <= array.length)
                return;

            // create bigger array and copy old data to it
            Object[] temp = new Object[minCapacity];
            T[] newArray = (T[])temp;

            // copy old data
            for (int i=0; i<actualSize; i++)
                newArray[i] = array[i];

            // replace the old array
            array = newArray;
        }
    }


    public T get(int index)
    {
        // If element is within the array size
        if (index >= 0 && index < actualSize)
            return array[index];
        else
            return null;
    }


    public int indexOf(T object)
    {
        int curIndex = 0;
        for (T obj: array)
        {
            if (obj == object)
                return curIndex;
            curIndex++;
        }

        return -1;
    }


    public T set(int index, T element)
    {
        // If element is within the array size
        if (index >= 0 && index < actualSize)
        {
            T prevObj = array[index];
            array[index] = element;

            // Return previous object on that position
            return prevObj;
        }
        else
            return null;
    }


    public T remove(int index)
    {
        // Return null if try to remove object outside of the array
        if (index < 0 || index >= actualSize)
            return null;

        T prevObj = get(index);
        if (prevObj != null)
        {
            // Move all objects down in the array (fill the gap)
            for (int i=index+1; i<actualSize; i++)
                array[i-1] = array[i];

            // Decrease the actual array length
            actualSize--;

            // Return previous object on that position
            return prevObj;
        }
        else
            return null;
    }


    public int size()
    {
        return actualSize;
    }




    @Override
    public Iterator<T> iterator()
    {
        return new Iterator<T>()
        {
            int lastIndex = -1;

            @Override
            public boolean hasNext()
            {
                int testIndex = lastIndex + 1;

                // If index in the next position is in the array border
                if (testIndex >= 0 && testIndex < actualSize)
                    return true;
                return false;
            }

            @Override
            public T next()
            {
                if (hasNext())
                {
                    lastIndex++;
                    return array[lastIndex];
                }
                else
                    return null; // NullPointerException
            }

            @Override
            public void remove()
            {
                if (lastIndex >= 0 && lastIndex < actualSize)
                    MyList.this.remove(lastIndex);
            }
        };
    }
}
