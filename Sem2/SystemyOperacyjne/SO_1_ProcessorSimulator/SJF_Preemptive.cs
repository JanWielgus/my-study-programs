using System;
using System.Collections.Generic;
using System.Text;

/*
 * Prreemptive scheduling algorithm executes tasks from
 * the shortest one and end on the longest
 * When new process comes and it is the shortest
 * it is executed instead the currently executed process
 * and this process goes right after the new shortest.
 * */


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

            // Sort all processes (at index 0 is the shortest process)
            processList.Sort(new ProcessComparer());
        }
    }
}
