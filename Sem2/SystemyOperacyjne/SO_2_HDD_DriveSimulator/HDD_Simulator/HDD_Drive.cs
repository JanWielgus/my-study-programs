using System;
using System.Collections.Generic;
using System.Text;
using SO_2_HDD_DriveSimulator.HDD_Simulator.Algorithms;


/*
 * HDD Drive have an arm and have sum of the all movements
 * 
 * */


namespace SO_2_HDD_DriveSimulator.HDD_Simulator
{
    class HDD_Drive
    {

        private SchedulingAlgorithm schedulingAlgorithm;
        private Sum armMovementSum;


        HDD_Drive(SchedulingAlgorithm schedulingAlgorithm)
        {
            this.schedulingAlgorithm = schedulingAlgorithm;
            armMovementSum = new Sum();
        }



        // PUT CODE THERE
        // ...
    }
}
