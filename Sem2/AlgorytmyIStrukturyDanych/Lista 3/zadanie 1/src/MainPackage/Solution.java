package MainPackage;

import java.util.Stack;

public class Solution
{
    Stack<Character> charStack;

    public static final int OPENING = 0;
    public static final int CLOSING = 1;
    public static final char [] OpeningChars = {'{', '[', '('};
    public static final char [] ClosingChars = {'}', ']', ')'};
    static final int AmtOfBrackets = 3;


    public Solution()
    {
        charStack = new Stack<>();
    }

    public int solution(String S)
    {
        for (int i=0; i < S.length(); i++)
        {
            char curCh = S.charAt(i);
            System.out.println("Char at: " + curCh);
            int type = checkType(curCh);

            if (type == OPENING)
            {
                // Put on the stack
                charStack.push(curCh);
            }
            else if (type == CLOSING)
            {
                // Check if match

                // If there is closing bracket but stack if empty this is not properly nested string
                if (charStack.empty())
                    return 0;

                char top = charStack.pop();
                //System.out.println("Comparing: " + top + " and " + curCh);
                if (!compareTwoBrackets(curCh, top))
                    return 0;
                System.out.println("passed");
            }
        }

        // 1 only if after all checking stack is empty
        if (charStack.empty())
            return 1;
        return 0;
    }


    int checkType(char ch)
    {
        for (int i=0; i<AmtOfBrackets; i++)
        {
            if (ch == OpeningChars[i])
                return OPENING;
            else if (ch == ClosingChars[i])
                return CLOSING;
        }

        throw new MyRuntimeException("This exception should not take place");
    }


    boolean compareTwoBrackets(char first, char second)
    {
        if (checkType(first) == checkType(second))
            return false;

        if (bracketIndex(first) == bracketIndex(second))
            return true;
        return false;
    }


    int bracketIndex(char bracket)
    {
        for (int i=0; i < AmtOfBrackets; i++)
        {
            if (bracket == OpeningChars[i] || bracket == ClosingChars[i])
                return i;
        }

        throw new MyRuntimeException("This exception should not take place.");
    }


    private class MyRuntimeException extends RuntimeException
    {
        MyRuntimeException(String str)
        {
            super(str);
        }
    }
}
