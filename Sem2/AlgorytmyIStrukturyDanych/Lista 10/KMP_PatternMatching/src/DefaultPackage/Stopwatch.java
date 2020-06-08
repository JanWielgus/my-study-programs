package DefaultPackage;

public class Stopwatch
{
    private long startTime; // ns
    private long duration; // ns

    public void start()
    {
        startTime = System.nanoTime();
    }

    public void stop()
    {
        duration = System.nanoTime() - startTime;
    }

    public double getDurationInSec()
    {
        return (double) duration / 1000000000.0;
    }
}
