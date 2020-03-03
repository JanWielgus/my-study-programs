using System;
using System.Collections.Generic;
using System.Text;

namespace SO_1_ProcessorSimulator
{
    class Process
    {
        private int totalExecutionTime = 0;
        private int remainingExecutionTime = 0;
        private int waitingTime;
        private bool isDone = false;
        private int id;
        private static int lastGivenID = 0;



        public Process(int executionTime)
        {
            // Give an ID to this task
            lastGivenID++;
            id = lastGivenID;

            TotalExecutionTime = executionTime;
            RemainingExecutionTime = TotalExecutionTime;
        }


        // Final taks execution
        // Just shows a message
        private void execute()
        {
            Console.WriteLine("Task " + id + " has just ended!");
            isDone = true;
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


        public int WaitingTime
        {
            get { return waitingTime; }
            set { waitingTime = value; }
        }


        public int ID { get { return id; } }




        // >>>>>>>>>>> Methods


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
    }
}
