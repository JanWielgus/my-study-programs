﻿using System;
using System.Collections.Generic;
using System.Text;

/*
 * FCFS - First Came First Served
 * */

namespace SO_2_HDD_DriveSimulator.HDD_Simulator.Algorithms
{
    class FCFS : SchedulingAlgorithm
    {
        public override Instruction getNextInstruction()
        {
            return instructionList[0];
        }
    }
}
