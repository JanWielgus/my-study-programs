package MainPackage;

// Veloso's Traversable Stack


public class VTStack<T> extends MyStack<T>
{
    private int cursor = 0; // index
    private int downDist = 0; // distance from the top of the stack


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
        downDist = 0;
    }


    @Override
    public T pop() throws EmptyStackException
    {
        T result = super.pop();
        cursor = lastIndex;
        downDist = 0;
        return result;
    }


    @Override
    public T top() throws EmptyStackException
    {
        T result = super.top();
        cursor = lastIndex; // set index to the last element
        downDist = 0;
        return result;
    }


    // return false if cursor is at the bottom of the stack
    public boolean down()
    {
        if (downDist < amtOfElem-1)
        {
            downDist++;
            cursor = correctIndex(cursor-1);
            return true;
        }
        return false;
    }


}
