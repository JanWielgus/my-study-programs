package DefaultPackage;

import java.util.List;

public class KMP_TimeTests
{
    public static void main(String[] args)
    {
        System.out.println("dziala");
        String text = "AABAACAADAABAABA";
        String pattern = "AABA";
        List<Integer> testList = KMP_PatternMatching.search(text, pattern);
        System.out.println(testList);
    }
}
