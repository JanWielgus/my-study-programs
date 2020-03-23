package MainPackage;

// Sinking stack

public class MyStack<T> implements IStack<T>
{
    protected int Size; // stacks size
    protected T[] array;
    protected int lastIndex;
    protected int amtOfElem;


    MyStack(int size)
    {
        Size = size;

        array = (T[]) new Object[Size];
        lastIndex = 0;
        amtOfElem = 0;
    }

    @Override
    public boolean isEmpty()
    {
        return amtOfElem == 0;
    }

    @Override
    public boolean isFull()
    {
        return amtOfElem == Size;
    }

    @Override
    public T pop() throws EmptyStackException
    {
        if (isEmpty())
            throw new EmptyStackException();

        T result = array[lastIndex]; // prepare the result value
        lastIndex = correctIndex(lastIndex-1); // decrease index and keep in border
        amtOfElem--;
        return result;
    }

    @Override
    public void push(T elem) throws FullStackException
    {
        if (isFull())
            throw new FullStackException();

        lastIndex = correctIndex(lastIndex+1); // increment the last index
        array[lastIndex] = elem; // put the element on prepared place
        amtOfElem++; // increase amt of all elements
    }

    @Override
    public int size()
    {
        return amtOfElem;
    }

    @Override
    public T top() throws EmptyStackException
    {
        if (isEmpty())
            throw new EmptyStackException();

        return array[lastIndex];
    }


    protected int correctIndex(int index)
    {
        if (index >= Size)
            index -= Size;
        else if (index < 0)
            index += Size;

        return index;
    }
}
