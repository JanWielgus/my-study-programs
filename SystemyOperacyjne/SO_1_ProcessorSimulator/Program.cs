using System;

namespace SO_1_ProcessorSimulator
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Hello World!");

            Process testProcess = new Process(10);

            testProcess.executeFor(4);
            testProcess.executeFor(4);
            testProcess.executeFor(4);

        }
    }
}
