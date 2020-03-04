using System;
using System.Collections.Generic;
using System.Text;

namespace SO_1_ProcessorSimulator
{
    class Rotary : SchedulingAlgorithm
    {
        private int nextProcessIndex = 0;

        public override Process getNextProcess()
        {
            Process next = processList[nextProcessIndex];
            changeNextIndex(1);
            return next;
        }


        public override bool removeProcess(Process process)
        {
            if (base.removeProcess(process))
            {
                // If removed any element then move back
                // To prevent skipping processes
                // (When process is removed then the next is at the same index)
                changeNextIndex(0);
                return true;
            }

            return false;
        }

        
        // Add a value to the next index in the array
        // And correct the value if needed
        private void changeNextIndex(int valueToAdd)
        {
            nextProcessIndex += valueToAdd;

            if (nextProcessIndex >= processList.Count)
                nextProcessIndex -= processList.Count;
            else if (nextProcessIndex < 0)
                nextProcessIndex += processList.Count;
        }
    }
}
