package MainPackage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.zip.Adler32;

public class VTStackTest
{
    VTStack<Integer> stack;

    @BeforeEach
    void setup() throws IStack.FullStackException
    {
        stack = new VTStack<>(5);

        // add some initial values
        stack.push(5);
        stack.push(10);
        stack.push(15);
    }


    @Test
    void generalTest1() throws IStack.FullStackException, IStack.EmptyStackException
    {
        Assertions.assertEquals(3, stack.size());

        stack.down();
        stack.push(123);
        Assertions.assertEquals(123, stack.peek());
        stack.down();
        stack.down();
        Assertions.assertEquals(10, stack.peek());
        stack.top();
        Assertions.assertEquals(123, stack.pop());
        Assertions.assertEquals(15, stack.pop());
        stack.pop();
        stack.pop();
        Assertions.assertEquals(true, stack.isEmpty());
        Assertions.assertEquals(0, stack.size());
    }


    @Test
    void peekTest() throws IStack.EmptyStackException, IStack.FullStackException
    {
        Assertions.assertEquals(15, stack.peek());
        stack.pop();
        stack.pop();
        Assertions.assertEquals(5, stack.peek());
        stack.pop();
        Assertions.assertThrows(IStack.EmptyStackException.class, stack::peek);
        stack.push(90);
        Assertions.assertEquals(90, stack.peek());
    }


    @Test
    void downTest() throws IStack.FullStackException, IStack.EmptyStackException
    {
        stack.push(2837);
        stack.down();
        stack.down();
        stack.down();
        Assertions.assertEquals(5, stack.peek());

        // if try to go down from the last element should return false
        Assertions.assertEquals(false, stack.down());
    }
}
