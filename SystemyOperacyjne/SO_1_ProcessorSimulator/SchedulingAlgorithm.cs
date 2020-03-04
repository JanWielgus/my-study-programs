using System;
using System.Collections.Generic;
using System.Text;

namespace SO_1_ProcessorSimulator
{
    abstract class SchedulingAlgorithm
    {
        protected List<Process> processList;
        Average averageTurnaroundTime;

        public SchedulingAlgorithm()
        {
            // create a processes list
            processList = new List<Process>();

            averageTurnaroundTime = new Average();
        }

        
        // Add new process to the list
        public void addProcess(Process process)
        {
            process.AddedToProcessorTime = ProgramTimer.getTime();

            processList.Add(process);
        }


        // Add a whole 
        public void addProcessSequence(List<Process> processListToAdd)
        {
            foreach (Process process in processListToAdd)
            {
                addProcess(process);
            }
        }


        // Remove process from the list
        public virtual bool removeProcess(Process process)
        {
            process.ExecutionEndTime = ProgramTimer.getTime();
            averageTurnaroundTime.addNewValue(process.getTurnaroundTime());

            return processList.Remove(process);
        }


        public int getAmtOfAwaitingProcesses()
        {
            return processList.Count;
        }


        public float getAverageTurnaroundTime()
        {
            return averageTurnaroundTime.getAverage();
        }


        // Return next process that have to be executed for a given time by the processor
        public abstract Process getNextProcess();
    }
}
