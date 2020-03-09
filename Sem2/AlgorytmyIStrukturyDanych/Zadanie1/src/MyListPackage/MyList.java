package MyListPackage;

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

        // Check if need to extend the array size
        if (actualSize+1 > array.length)
            if (actualSize < defaultCapacity)
                ensureCapacity(defaultCapacity);
            else
                ensureCapacity(actualSize*2); // double the previous capacity

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
        for (int i)
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

    }


    public int indexOf(T object)
    {

    }


    public void set(int index, T element)
    {

    }


    public void remove(int index)
    {

    }


    public int size()
    {

    }




    @Override
    public Iterator<T> iterator()
    {
        return new Iterator<T>()
        {
            int lastIndex = 0;

            @Override
            public boolean hasNext()
            {
                if (lastIndex >=0 && lastIndex < actualSize-1)
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
                
            }
        };
    }
}
