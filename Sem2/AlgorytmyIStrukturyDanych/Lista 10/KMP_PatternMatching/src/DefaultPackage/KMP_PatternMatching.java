package DefaultPackage;

import java.util.ArrayList;
import java.util.List;

public class KMP_PatternMatching
{
    static List<Integer> search(String text, String pattern)
    {
        if (text == null)
            throw new NullPointerException("Passed text cannot be null");
        if (pattern == null)
            throw new NullPointerException("Passed pattern cannot be null");

        int textLength = text.length();
        int patternLength = pattern.length();
        List<Integer> matchingIndexes = new ArrayList<>();

        // pre-processing
        int[] pi = computePrefixFunction(pattern);
        //for (int p: pi)
            //System.out.println(p);

        int q = 0; // index in pattern
        int i = 0; // index in text

        while (i < textLength)
        {
            // If letters are equal, increment indexes
            if (pattern.charAt(q) == text.charAt(i))
            {
                q++;
                i++;
            }

            // Found a matching pattern
            if (q == patternLength)
            {
                // Add matching index to the result array
                matchingIndexes.add(i - q);

                // Change the pattern index
                q = pi[q - 1];
            }
            else if (pattern.charAt(q) != text.charAt(i))
            {
                if (q != 0)
                    q = pi[q - 1];
                else
                    i = i + 1;
            }
        }

        return matchingIndexes;
    }



    private static int[] computePrefixFunction(String pattern)
    {
        int patternLength = pattern.length();
        int[] pi = new int[patternLength]; // return array

        int lastLen = 0; // len of the previous longest prefix suffix
        pi[0] = 0;

        int i = 1;
        while (i < patternLength)
        {
            if (pattern.charAt(i) == pattern.charAt(lastLen))
            {
                lastLen++;
                pi[i] = lastLen;
                i++;
            }
            else // (pattern[i] != pattern[len])
            {
                if (lastLen == 0)
                {
                    pi[i] = lastLen;
                    i++;
                }
                else
                    lastLen = pi[lastLen - 1];
            }
        }

        return pi;
    }
}
