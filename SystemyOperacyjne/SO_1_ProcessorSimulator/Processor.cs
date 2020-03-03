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
            // And delete it if it was completed
            Process currentPorcess = schedulingAlgorithm.getNextProcess();
            bool result = currentPorcess.executeFor(timeSlice);
            if (result == true) // when execution was ended
                schedulingAlgorithm.removeProcess(currentPorcess);


            // If there is no awaiting processes return true
            if (schedulingAlgorithm.getAmtOfAwaitingProcesses() == 0)
                return true;
            else
                return false;
        }

    }
}
