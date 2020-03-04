/*
 * Author: Jan Wielgus
 * Date: 03.02.2020
 */

using System;
using System.Collections.Generic;



/*
 * This is a processor scheduling algorithms simulator
 * 
 * 
 * This program should be designed in a different way:
 * - Processor should contain a list of processes (not scheduling algorihms as it is now)
 * 
 */

namespace SO_1_ProcessorSimulator
{
    class Program
    {
        private const int AmtOfSequences = 3;
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

                int amtOfProcesses = random.Next(3, 6); // SETTINGS: min/max of processes in this sequence

                for (int j = 0; j < amtOfProcesses; j++)
                    currentList.Add(new Process(random.Next(2, 12))); // SETTING: min/max execution time for this process
            }



            // Make a copies for each processor
            List<List<Process>> processListCopy1 = getProcessSequenceCopy(processSequenceArray);
            List<List<Process>> processListCopy2 = getProcessSequenceCopy(processSequenceArray);
            List<List<Process>> processListCopy3 = getProcessSequenceCopy(processSequenceArray);
            List<List<Process>> processListCopy4 = getProcessSequenceCopy(processSequenceArray);

            //showProcessSequence(processSequenceArray);
            //showProcessSequence(processListCopy1);
            //showProcessSequence(processListCopy2);

            // Execute processors one by one
            // While processor is working add next processes sequences at specified order
            // Processor make time ticks


            executeProcessor(new Processor(new FCFS(), TimeSlice),
                processListCopy1,
                "PROCESSOR 1 - FCFS");


            executeProcessor(new Processor(new SJF_NonPreemptive(), TimeSlice),
                processListCopy2,
                "PROCESSOR 2 - SJF(Non - preemptive)");

    
            executeProcessor(new Processor(new SJF_Preemptive(), TimeSlice),
                processListCopy3,
                "PROCESSOR 2 - SJF (Preemptive)");


            executeProcessor(new Processor(new Rotary(), TimeSlice),
                processListCopy4,
                "PROCESSOR 3 - Rotary");

        }



        
        static List<List<Process>> getProcessSequenceCopy(List<List<Process>> toCopy)
        {
            List<List<Process>> copy = new List<List<Process>>();

            for (int i = 0; i < toCopy.Count; i++)
                copy.Add(getProcessListCopy(toCopy[i]));

            return copy;
        }

        static List<Process> getProcessListCopy(List<Process> listToCopy)
        {
            List<Process> copy = new List<Process>();
            
            for (int i=0; i<listToCopy.Count; i++)
                copy.Add(getProcessCopy(listToCopy[i]));

            return copy;
        }

        static Process getProcessCopy(Process processToCopy)
        {
            return new Process(processToCopy);
        }


        static void executeProcessor(Processor processor, List<List<Process>> listOfProcesses, string text)
        {
            Console.WriteLine("   >>>>><    " + text + "    ><<<<<");
            Console.WriteLine();

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


            // Show average turnaound time
            Console.WriteLine();
            Console.WriteLine("Average turnaround time: " + processor.getAverageTurnaroundTime());
            Console.WriteLine("<<<<<   ENDED");
            Console.WriteLine();
            Console.WriteLine();
        }


        static void showProcessSequence(List<List<Process>> processSequence)
        {
            Console.WriteLine("Showing process sequence");

            foreach (var list in processSequence)
            {
                foreach (var process in list)
                {
                    Console.WriteLine("Task " + process.ID + " total time: " + process.TotalExecutionTime);
                }
            }

            Console.WriteLine("end");
            Console.WriteLine();
        }

    }
}
