package MainPackage;

// Veloso's Traversable Stack


public class VTStack<T> extends MyStack<T>
{
    private int cursor = 0; // index


    public VTStack(int size)
    {
        super(size);
    }


    public T peek() throws EmptyStackException
    {
        if (isEmpty())
            throw new EmptyStackException();

        return array[cursor];
    }


    @Override
    public void push(T elem) throws FullStackException
    {
        super.push(elem);
        cursor = lastIndex;
    }


    @Override
    public T pop() throws EmptyStackException
    {
        T result = super.pop();
        cursor = lastIndex;
        return result;
    }


    @Override
    public T top() throws EmptyStackException
    {
        T result = super.top();
        cursor = size()-1; // set index to the last element
        return result;
    }


    // return false if cursor is at the bottom of the stack
    public boolean down()
    {
        if (cursorDistanceFromTop(lastIndex) < amtOfElem)
        {
            cursor = correctIndex(cursor-1);
            return true;
        }
        return false;
    }

/*
    private int distanceFromLastElementIndex(int index)
    {
        int lastElementIndex = correctIndex(lastIndex - size());

        if (lastElementIndex <= cursor)
            return cursor-lastElementIndex;
        else
            return (Size + cursor) - lastElementIndex;
    }*/

    private int cursorDistanceFromTop(int index)
    {
        if (index <= cursor)
            return cursor - index;
        else
            return (Size + cursor) - index;
    }


}
