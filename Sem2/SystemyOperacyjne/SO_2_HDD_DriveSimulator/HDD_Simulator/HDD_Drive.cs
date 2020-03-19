using System;
using System.Collections.Generic;
using System.Text;


/*
 * HDD Drive have an arm and have sum of the all movements
 * 
 * */


namespace SO_2_HDD_DriveSimulator.HDD_Simulator
{
    class HDD_Drive
    {
        private Algorithms.SchedulingAlgorithm schedulingAlgorithm;
        private Sum armMovementSum;


        HDD_Drive()
        {
            armMovementSum = new Sum();
        }
    }
}
