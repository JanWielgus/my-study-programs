package MainPackage;

// Sinking stack

public class MyStack<T> implements IStack<T>
{
    int Size; // stacks size

    MyStack(int size)
    {
        Size = size;
    }

    @Override
    public boolean isEmpty()
    {
        return false;
    }

    @Override
    public boolean isFull()
    {
        return false;
    }

    @Override
    public T pop() throws EmptyStackException
    {
        return null;
    }

    @Override
    public void push(T elem) throws FullStackException
    {

    }

    @Override
    public int size()
    {
        return 0;
    }

    @Override
    public T top() throws EmptyStackException
    {
        return null;
    }
}
