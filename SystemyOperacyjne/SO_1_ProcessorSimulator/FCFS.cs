using System;
using System.Collections.Generic;
using System.Text;

namespace SO_1_ProcessorSimulator
{
    class FCFS : SchedulingAlgorithm
    {
        public FCFS()
        {
            // do nothing
        }


        public override void addProcess(Process process)
        {
            base.addProcess(process);
            if (processList.Count == 1)
                processList[0].WaitingTime = 0;
            else
            {
                int curIndex = processList.Count - 1;
                processList[curIndex].WaitingTime = processList[curIndex - 1].WaitingTime +
                    processList[curIndex - 1].TotalExecutionTime;
            }
                
        }


        public override float getAverageTurnaroundTime()
        {
            int sum = 0;
            
            for (int i=0; i<processList.Count; i++)
                sum += processList[i].WaitingTime;

            return (float)sum / processList.Count;
        }


        public override Process getNextProcess()
        {
            return processList[0];
        }


        public override bool removeProcess(Process process)
        {
            if (base.removeProcess(process))
            {
                // update waiting time
                processList[0].WaitingTime = 0;
                for (int i = 1; i < processList.Count; i++)
                    processList[i].WaitingTime = processList[i - 1].WaitingTime + processList[i - 1].TotalExecutionTime;

                return true;
            }

            return false;
        }
    }
}
