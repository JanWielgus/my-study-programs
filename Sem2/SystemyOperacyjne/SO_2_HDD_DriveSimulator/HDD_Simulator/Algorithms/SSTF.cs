using System;
using System.Collections.Generic;
using System.Text;


/*
 * SSTF - Shortest Seek Time First
 * */


namespace SO_2_HDD_DriveSimulator.HDD_Simulator.Algorithms
{
    class SSTF : SchedulingAlgorithm
    {
        public override Instruction getNextInstruction()
        {
            // Prevent from accessing the empty array
            if (instructionList.Count == 0)
                throw new IndexOutOfRangeException("Array has no elements");


            // Finding the shortest seek time instruciton
            Instruction SST_instr = instructionList[0]; // shortest seek time instruciton
            int shortestSeekTime = getSeekTime(SST_instr); // its actual seek time

            foreach (Instruction instr in instructionList)
            {
                int currentSeekTime = getSeekTime(instr);
                if (currentSeekTime < shortestSeekTime)
                {
                    shortestSeekTime = currentSeekTime;
                    SST_instr = instr;
                }
            }

            return SST_instr;
        }


        // Specific for this algorithm
        // 0 is the min return value
        // Takes the absolute distance
        private int getSeekTime(Instruction instr)
        {
            return Math.Abs(instr.getAddress() - driveArm.getCurrentAddress());
        }
    }
}
