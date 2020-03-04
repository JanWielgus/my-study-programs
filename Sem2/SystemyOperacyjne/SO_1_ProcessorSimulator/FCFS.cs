using System;
using System.Collections.Generic;
using System.Text;

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
