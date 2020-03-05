using System;
using System.Collections.Generic;
using System.Diagnostics.CodeAnalysis;
using System.Text;

/*
 * Non prreemptive scheduling algorithm executes tasks from
 * the shortest one and end on the longest
 * When new process comes and it is the shortest
 * it always goes after the currently executed process
 * */


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

            // Sort processes excluding the currently executed one (just start from indes 1)
            processList.Sort(1, processList.Count-1, new ProcessComparer());
        }

    }
}
