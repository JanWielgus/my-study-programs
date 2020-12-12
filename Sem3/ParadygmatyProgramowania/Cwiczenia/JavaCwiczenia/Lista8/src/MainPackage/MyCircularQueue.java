package MainPackage;

import java.util.ArrayList;

/**
 * Circular queue implementation.
 * @param <E>
 */
public class MyCircularQueue<E> implements MyQueue<E>
{
    private ArrayList<E> array;
    private E NullElem;
    private int front; // index of the first element
    private int back; // index of the last element
    // queue is empty if back == -1
    // if front == back then queue has one element
    // if front is one place after back, queue is full

    public MyCircularQueue(int queueSize)
    {
        array = new ArrayList<>(queueSize);
        for (int i=0; i < queueSize; i++)
            array.add(null);
        array.trimToSize();

        front = 0;
        back = -1;
    }

    @Override
    public void enqueue(E x) throws FullException
    {
        if (isFull())
            throw new FullException("Queue is full");

        back++;
        back %= array.size();
        array.set(back, x);
    }

    @Override
    public void dequeue()
    {
        if (isEmpty())
            return;

        if (front == back) // if this is the last element
            back = -1;
        else
        {
            front++;
            front %= array.size();
        }
    }

    @Override
    public E first() throws EmptyException
    {
        if (isEmpty())
            throw new EmptyException("Queue is empty");
        return array.get(front);
    }

    @Override
    public boolean isEmpty()
    {
        return back == -1;
    }

    @Override
    public boolean isFull()
    {
        if (isEmpty())
            return false;
        return ((back == array.size()-1 && front == 0) || (back == front-1));
    }
}
