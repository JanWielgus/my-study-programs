package MainPackage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class MyStackTest
{
    MyStack<Integer> stack;

    @BeforeEach
    void setup() throws IStack.FullStackException
    {
        stack = new MyStack<>(5);

        // add some initial values
        stack.push(5);
        stack.push(10);
        stack.push(15);
    }

    @Test
    void generalStackTest1() throws IStack.FullStackException, IStack.EmptyStackException
    {
        stack.push(1);
        stack.push(2);
        int[] correct = {2, 1, 15, 10, 5};

        // Check size
        Assertions.assertEquals(correct.length, stack.size());

        // Check elements
        for (int i=0; i < correct.length; i++)
        {
            Assertions.assertEquals(correct[i], stack.pop());

        }

        // Check if empty after all
        Assertions.assertEquals(true, stack.isEmpty());
        Assertions.assertEquals(0, stack.size());
    }


    @Test
    void fullStackTest() throws IStack.FullStackException
    {
        stack.push(123);
        stack.push(4523);

        Assertions.assertEquals(true, stack.isFull());
        Assertions.assertThrows(IStack.FullStackException.class, () -> {
            stack.push(0);
        });
    }

    @Test
    void emptyStackTest()throws IStack.EmptyStackException, IStack.FullStackException
    {
        stack.push(123213);
        stack.push(123);

        Assertions.assertEquals(false, stack.isEmpty());

        // Make stack empty
        for (int i=5; i>0; i++)
            stack.pop();

        Assertions.assertEquals(true, stack.isEmpty());
        Assertions.assertThrows(IStack.EmptyStackException.class, stack::pop);
        Assertions.assertThrows(IStack.EmptyStackException.class, stack::top);
        Assertions.assertEquals(0, stack.size());
    }

    @Test
    void testStackTop() throws IStack.EmptyStackException
    {
        Assertions.assertEquals(15, stack.top());
        Assertions.assertEquals(3, stack.size());
        Assertions.assertEquals(15, stack.pop());
        Assertions.assertEquals(10, stack.top());
    }


}
