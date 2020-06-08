package DefaultPackage;

import java.util.ArrayList;
import java.util.List;

public class KMP_PatternMatching
{
    static List<Integer> search(String text, String pattern)
    {
        int textLength = text.length();
        int patternLength = pattern.length();
        List<Integer> matchingIndexes = new ArrayList<>();

        // pre-processing
        int[] pi = computePrefixFunction(pattern);

        int q = 0;
        for (int i=0; i<textLength; i++)
        {
            while (q > 0 && pattern.charAt(q+1) != text.charAt(i))
                q = pi[q];

            if (pattern.charAt(q+1) == text.charAt(i))
                q++;

            if (q == patternLength)
            {
                matchingIndexes.add(i - patternLength);
                q = pi[q];
            }
        }

        return matchingIndexes;
    }



    private static int[] computePrefixFunction(String pattern)
    {
        int length = pattern.length();
        int[] prefFuncValues = new int[length];

        prefFuncValues[0] = 0;
        int k = 0;
        for (int q = 1; q < length; q++)
        {
            // try to decrease k (all previous values are smaller)
            while (k > 0 && pattern.charAt(k + 1) != pattern.charAt(q))
                k = prefFuncValues[k];

            // if there is a pattern, increase k
            if (pattern.charAt(k + 1) == pattern.charAt(q))
                k++;

            // update prefix function value at index q
            prefFuncValues[q] = k;
        }

        return prefFuncValues;
    }
}
