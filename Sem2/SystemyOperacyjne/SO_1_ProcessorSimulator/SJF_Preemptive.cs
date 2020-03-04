using System;
using System.Collections.Generic;
using System.Text;

namespace SO_1_ProcessorSimulator
{
    class SJF_Preemptive : SchedulingAlgorithm
    {
        public override Process getNextProcess()
        {
            return processList[0];
        }

        public override void addProcess(Process process)
        {
            base.addProcess(process);

            processList.Sort(new ProcessComparer());
        }
    }
}
