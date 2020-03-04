using System;
using System.Collections.Generic;
using System.Text;

namespace SO_1_ProcessorSimulator
{
    class Processor
    {
        private SchedulingAlgorithm schedulingAlgorithm; // used scheduling algorithm
        private int timeSlice = 1; // kwant czasu

           
        public Processor(SchedulingAlgorithm schedulingAlgorithm, int timeSlice)
        {
            this.schedulingAlgorithm = schedulingAlgorithm;
            this.timeSlice = timeSlice;
        }


        // Execute next process
        // Return true if there is no new process
        public bool executeNextTask()
        {
            // If there is no awaiting processes return true
            if (schedulingAlgorithm.getAmtOfAwaitingProcesses() == 0)
                return true;

            // Execute the next time for this processor's time-slice (kwant czasu)
            // And delete it if it was completed
            Process currentPorcess = schedulingAlgorithm.getNextProcess();
            bool result = currentPorcess.executeFor(timeSlice);
            if (result == true) // when execution was ended
            {
                schedulingAlgorithm.removeProcess(currentPorcess);
                Console.WriteLine("\tAverage turnaround time " + getAverageTurnaround());
                Console.WriteLine("\tCount: " + schedulingAlgorithm.getAmtOfAwaitingProcesses());
            }


            if (schedulingAlgorithm.getAmtOfAwaitingProcesses() == 0)
                return true;
            else
                return false;
        }


        public void addProcess(Process process)
        {
            schedulingAlgorithm.addProcess(process);
        }

        public void addProcessSequence(List<Process> processListToAdd)
        {
            schedulingAlgorithm.addProcessSequence(processListToAdd);
        }


        public float getAverageTurnaround()
        {
            return schedulingAlgorithm.getAverageTurnaroundTime();
        }

    }
}
