using System;
using System.Collections.Generic;

namespace SO_1_ProcessorSimulator
{
    class Program
    {
        private const int amtOfSequences = 5;

        static void Main(string[] args)
        {
            Console.WriteLine("Hello World!");


            List<Process>[] processSequenceArray = new List<Process>[amtOfSequences];

            // Generate an array of random sequences of random processes
            Random random = new Random();
            for (int i=0; i<amtOfSequences; i++)
            {
                List<Process> currentList = processSequenceArray[i];
                currentList = new List<Process>();

                int amtOfProcesses = random.Next(3, 10); // min/max of processes in this sequence

                for (int j = 0; j < amtOfProcesses; j++)
                    currentList.Add(new Process(random.Next(5, 30))); // min/max execution time for this process
            }



            // Make a copies for each processor
            List<Process>[] processCopy1 = new List<Process>[amtOfSequences];
            List<Process>[] processCopy2 = new List<Process>[amtOfSequences];
            List<Process>[] processCopy3 = new List<Process>[amtOfSequences];
            processSequenceArray.CopyTo(processCopy1, 0);
            processSequenceArray.CopyTo(processCopy2, 0);
            processSequenceArray.CopyTo(processCopy3, 0);


            // Create processors
            // .......

            // THERE I LEFT


            // Execute processors one by one
            // While processor is working add next processes sequences at specified order


            Console.WriteLine(" >>>  PROCESSOR 1 - FCFS");
            Console.WriteLine();

            //...

            Console.WriteLine();
            Console.WriteLine(" <<< ENDED");





            Console.WriteLine(" >>>  PROCESSOR 2 - SJF"); // wywłaszczenie i bez (o co chodzi ??? ) <-----
            Console.WriteLine();

            //...

            Console.WriteLine();
            Console.WriteLine(" <<< ENDED");





            Console.WriteLine(" >>>  PROCESSOR 3 - Rotary");
            Console.WriteLine();

            //...

            Console.WriteLine();
            Console.WriteLine(" <<< ENDED");


        }
    }
}
