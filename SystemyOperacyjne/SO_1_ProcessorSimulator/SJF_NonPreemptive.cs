using System;
using System.Collections.Generic;
using System.Diagnostics.CodeAnalysis;
using System.Text;

namespace SO_1_ProcessorSimulator
{
    class SJF_NonPreemptive : SchedulingAlgorithm
    {
        public override Process getNextProcess()
        {
            return processList[0];
        }

        public override void addProcess(Process process)
        {
            base.addProcess(process);

            processList.Sort(1, processList.Count-1, new ProcessComparer());
        }

    }
}
