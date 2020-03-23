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
        return null;
    }


    @Override
    public void push(T elem) throws FullStackException
    {

    }


    @Override
    public T pop() throws EmptyStackException
    {
        return null;
    }


    @Override
    public T top() throws EmptyStackException
    {
        cursor = size()-1; // set index to the last element
        return super.top();
    }


    // return false if
    public boolean down()
    {/*
        if (cursor == 0)
            return false;*/

        return false;
    }


}
