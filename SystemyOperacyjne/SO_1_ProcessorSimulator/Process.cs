using System;
using System.Collections.Generic;
using System.Text;

namespace SO_1_ProcessorSimulator
{
    class Process
    {
        private int totalExecutionTime = 0;
        private int remainingExecutionTime = 0;
        private bool isDone = false;
        private int id;

        // For time calculations
        private int addedToProcessorTime = -1;
        private int executionEndTime = -1;

        private static int lastGivenID = 0;



        public Process(int executionTime)
        {
            // Give an ID to this task
            lastGivenID++;
            id = lastGivenID;

            TotalExecutionTime = executionTime;
            RemainingExecutionTime = TotalExecutionTime;
        }


        // Copy constructor
        public Process(Process processToCopy)
        {
            totalExecutionTime = processToCopy.totalExecutionTime;
            remainingExecutionTime = processToCopy.remainingExecutionTime;
            isDone = processToCopy.isDone;
            id = processToCopy.id;
            addedToProcessorTime = processToCopy.addedToProcessorTime;
            executionEndTime = processToCopy.executionEndTime;
        }



        
        // >>>>>>>>>>> Properties


        public int TotalExecutionTime
        {
            get { return totalExecutionTime; }
            private set
            {
                if (value > 0)
                    totalExecutionTime = value;
                else
                    totalExecutionTime = 0;
            }
        }
        public int RemainingExecutionTime
        {
            get { return remainingExecutionTime; }
            private set
            {
                if (value > 0)
                    remainingExecutionTime = value;
                else
                    remainingExecutionTime = 0;
            }
        }

        public bool IsDone { get { return isDone; } }


        public int ID { get { return id; } }


        public int AddedToProcessorTime
        {
            get { return addedToProcessorTime; }
            set { addedToProcessorTime = value; }
        }

        public int ExecutionEndTime
        {
            get { return executionEndTime; }
            set { executionEndTime = value; }
        }





        // >>>>>>>>>>> Methods


        // Final taks execution
        // Just shows a message
        private void execute()
        {
            Console.WriteLine("Task " + id + " has just ended!");
            isDone = true;
        }


        // Executes this process for a given time
        // May be not ended in a given time
        // Autimatically executes execute() method when done
        // Returns true when ended execution
        public bool executeFor(int time)
        {
            // Decrease remaining time
            RemainingExecutionTime -= time;

            // If the whole task was completed then show the result
            if (RemainingExecutionTime == 0)
            {
                execute();
                return true;
            }
            else
            {
                Console.WriteLine("Task " + id + " suspended and waiting (remain " +
                    RemainingExecutionTime + " of total " + TotalExecutionTime + ")");

                return false;
            }
        }


        public int getTurnaroundTime()
        {
            if (AddedToProcessorTime == -1 || ExecutionEndTime == -1)
                return -1;

            return ExecutionEndTime - TotalExecutionTime - AddedToProcessorTime;
        }
    }
}
