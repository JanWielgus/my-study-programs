using System;
using System.Collections.Generic;
using System.Text;

namespace SO_1_ProcessorSimulator
{
    class Processor
    {
        private SchedulingAlgorithm schedulingAlgorithm; // used scheduling algorithm
        private int timeSlice = 1; // kwant czasu

           
        public Processor(SchedulingAlgorithm schedulingAlgorithm, int timeSlice)
        {
            this.schedulingAlgorithm = schedulingAlgorithm;
        }


        // Execute next process
        // Return true if there is no new process
        public bool executeNextTask()
        {
            // Execute the next time for this processor's time-slice (kwant czasu)
            schedulingAlgorithm.getNextProcess().executeFor(timeSlice);


            if (schedulingAlgorithm.getAmtOfAwaitingProcesses() == 0)
                return true;
            else
                return false;
        }

    }
}
