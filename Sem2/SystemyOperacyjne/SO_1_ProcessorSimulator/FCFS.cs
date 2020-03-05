using System;
using System.Collections.Generic;
using System.Text;

/*
 * This is just simple FIFO queue.
 * Executes processes in order that they were added.
 * */


namespace SO_1_ProcessorSimulator
{
    class FCFS : SchedulingAlgorithm
    {
        public override Process getNextProcess()
        {
            return processList[0];
        }

    }
}
