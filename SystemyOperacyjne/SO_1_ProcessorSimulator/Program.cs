using System;
using System.Collections.Generic;

namespace SO_1_ProcessorSimulator
{
    class Program
    {
        private const int AmtOfSequences = 5;
        private const int TimeSlice = 5;

        static void Main(string[] args)
        {
            Console.WriteLine("Hello World!");


            List<List<Process>> processSequenceArray = new List<List<Process>>();

            // Generate an array of random sequences of random processes
            Random random = new Random();
            for (int i=0; i<AmtOfSequences; i++)
            {
                processSequenceArray.Add(new List<Process>());
                List<Process> currentList = processSequenceArray[i];

                int amtOfProcesses = random.Next(3, 7); // min/max of processes in this sequence

                for (int j = 0; j < amtOfProcesses; j++)
                    currentList.Add(new Process(random.Next(5, 12))); // min/max execution time for this process
            }



            // Make a copies for each processor
            List<List<Process>> processListCopy1 = getCopy(processSequenceArray);
            List<List<Process>> processListCopy2 = getCopy(processSequenceArray);
            List<List<Process>> processListCopy3 = getCopy(processSequenceArray);



            // Execute processors one by one
            // While processor is working add next processes sequences at specified order


            Console.WriteLine(" >>>  PROCESSOR 1 - FCFS");
            Console.WriteLine();
            executeProcessor(new Processor(new FCFS(), TimeSlice), processListCopy1);
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


        static List<List<Process>> getCopy(List<List<Process>> toCopy)
        {
            List<List<Process>> copy = new List<List<Process>>();

            for (int i=0; i<AmtOfSequences; i++)
            {
                copy.Add(new List<Process>(toCopy[i]));
            }

            return copy;
        }


        static void executeProcessor(Processor processor, List<List<Process>> listOfProcesses)
        {
            int count = 0;
            processor.addProcessSequence(listOfProcesses[0]); // add the first process sequence

            int arrayIndex = 1; // index of next process sequence to add
            while (processor.executeNextTask() == false)
            {
                count++;

                // Add next processes sequence every 3rd processor execution
                if (arrayIndex < AmtOfSequences && count % 3 == 0)
                    processor.addProcessSequence(listOfProcesses[arrayIndex++]);
            }
        }
    }
}
