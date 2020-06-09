package DefaultPackage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class KMP_TimeTests
{
    static String longText;
    static String shortText;

    public static void main(String[] args)
    {
        // Read texts
        try
        {
            readTextsToStrings();
        } catch (IOException e)
        {
            e.printStackTrace();
        }


        runAndShowResults(longText, "ubranie",
                "Long text, short pattern");

        runAndShowResults(longText, "Szlachta głodna plądruje, zabiera co może:\n" +
                                            "Kropiciel, stanowisko zająwszy w oborze,\n" +
                                            "Jednego wołu i dwa cielce w łby zakropił,\n" +
                                            "A Brzytewka im szable w gardzielach utopił;",
                "Long text, long pattern");


        runAndShowResults(shortText, "jeszcze",
                "Short text, short pattern");

        runAndShowResults(shortText, "Wiały noc całą, a tak zasię skowyczały w polach kiej to stado zgłodniałych wilków",
                "Short text, long pattern");
    }



    static void runAndShowResults(String text, String pattern, String msg)
    {
        Stopwatch stopwatch = new Stopwatch();

        stopwatch.start();
        List<Integer> resultList = KMP_PatternMatching.search(text, pattern);
        stopwatch.stop();
        showResults(resultList, stopwatch.getDurationInSec(), msg);
    }


    static void showResults(List<Integer> resultList, double duration, String msg)
    {
        System.out.println();
        System.out.println(msg);
        System.out.print("Duration: ");
        System.out.printf("%.5f", duration);
        System.out.println(" [s]");
        System.out.println("Found " + resultList.size() + " time(s). Contents: ");
        for (int element: resultList)
            System.out.print(element + ", ");
        System.out.println();
    }


    static void readTextsToStrings() throws IOException
    {
        // long text
        InputStream is = new FileInputStream("PanTadeusz.txt");
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));

        String line = buf.readLine();
        StringBuilder sb = new StringBuilder();

        while(line != null)
        {
            sb.append(line).append("\n");
            line = buf.readLine();
        }

        longText = sb.toString();




        // short text
        is = new FileInputStream("ChlopiFragment.txt");
        buf = new BufferedReader(new InputStreamReader(is));

        line = buf.readLine();
        sb = new StringBuilder();

        while (line != null)
        {
            sb.append(line).append("\n");
            line = buf.readLine();
        }

        shortText = sb.toString();
    }
}
