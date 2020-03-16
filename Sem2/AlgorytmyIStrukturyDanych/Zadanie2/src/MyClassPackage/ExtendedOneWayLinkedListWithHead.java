package MyClassPackage;

import ClassFromLecture.*;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.function.Consumer;

public class ExtendedOneWayLinkedListWithHead<T>
        extends AbstractList<T> implements Iterable<T>
{
    private OneWayLinkedListWithHead<T> innerList;

    public ExtendedOneWayLinkedListWithHead()
    {
        innerList = new OneWayLinkedListWithHead<T>();
    }


    @Override
    public Iterator<T> iterator()
    {
        return this.listIterator();
    }

    @Override
    public ListIterator<T> listIterator()
    {
        return new ListIterator<T>()
        {
            int lastIndex = -1;

            @Override
            public void forEachRemaining(Consumer<? super T> action)
            {
                throw new UnsupportedOperationException();
            }


            @Override
            public boolean hasNext()
            {
                int testIndex = lastIndex + 1;

                // If index in the next position is in the array border
                if (inBorder(testIndex))
                    return true;
                return false;
            }

            @Override
            public T next()
            {
                if (hasNext())
                {
                    lastIndex++;
                    return get(lastIndex);
                }
                else
                    return null;
            }

            @Override
            public boolean hasPrevious()
            {
                int testIndex = lastIndex - 1;

                // If index in the prev position is in the array border
                if (inBorder(testIndex))
                    return true;
                return false;
            }

            @Override
            public T previous()
            {
                if (hasPrevious())
                {
                    lastIndex--;
                    return get(lastIndex);
                }
                else
                    return null;
            }

            @Override
            public int nextIndex()
            {
                if (hasNext())
                    return lastIndex + 1;
                else
                    return -1;
            }

            @Override
            public int previousIndex()
            {
                if (hasPrevious())
                    return lastIndex - 1;
                else
                    return -1;
            }

            @Override
            public void remove()
            {
                if (inBorder(lastIndex))
                    ExtendedOneWayLinkedListWithHead.this.remove(lastIndex);
            }

            @Override
            public void set(T t)
            {
                if (inBorder(lastIndex))
                    ExtendedOneWayLinkedListWithHead.this.set(lastIndex, t);
            }

            @Override
            public void add(T t)
            {
                if (inBorder(lastIndex))
                    ExtendedOneWayLinkedListWithHead.this.add(lastIndex, t);
            }

            boolean inBorder(int index)
            {
                if (index >= 0 && index < size())
                    return true;
                return false;
            }
        };
    }




    @Override
    public boolean add(T t)
    {
        return innerList.add(t);
    }

    @Override
    public void add(int index, T element)
    {
        innerList.add(index, element);
    }

    @Override
    public void clear()
    {
        innerList.clear();
    }

    @Override
    public boolean contains(T element)
    {
        return innerList.contains(element);
    }

    @Override
    public T get(int index)
    {
        return innerList.get(index);
    }

    @Override
    public T set(int index, T element)
    {
        return innerList.set(index, element);
    }

    @Override
    public int indexOf(T element)
    {
        return innerList.indexOf(element);
    }

    @Override
    public boolean isEmpty()
    {
        return innerList.isEmpty();
    }

    @Override
    public T remove(int index)
    {
        return innerList.remove(index);
    }

    @Override
    public boolean remove(T element)
    {
        return innerList.remove(element);
    }

    @Override
    public int size()
    {
        return innerList.size();
    }
}
