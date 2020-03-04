using System;
using System.Collections.Generic;
using System.Text;

namespace SO_1_ProcessorSimulator
{
    abstract class SchedulingAlgorithm
    {
        protected List<Process> processList;

        public SchedulingAlgorithm()
        {
            // create a processes list
            processList = new List<Process>();
        }

        
        // Add new process to the list
        public virtual void addProcess(Process process)
        {
            processList.Add(process);
        }

        /*
        // Create and add new process with random execution time
        public void addNewRandomProcess(int maxExecutionTime)
        {
            Random tempRandom = new Random();
            processList.Add(new Process(tempRandom.Next(0, maxExecutionTime)));
        }*/


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
            return processList.Remove(process);
        }


        public int getAmtOfAwaitingProcesses()
        {
            return processList.Count;
        }


        // Return next process that have to be executed for a given time by the processor
        public abstract Process getNextProcess();


        public abstract float getAverageTurnaroundTime();
    }
}
