using System;
using System.Collections.Generic;
using System.Text;

/*
 * Circular SCAN - After reacing the last address drive arm move to the beginning and start scanning again
 * Scan always in one direction
 * */



namespace SO_2_HDD_DriveSimulator.HDD_Simulator.Algorithms
{
    class C_SCAN : ScanBase
    {
        public override Instruction getNextInstruction()
        {
            if (instructionList.Count == 0)
                throw new IndexOutOfRangeException("Instruction list is empty");


            Instruction closestInstr = getClosestInstruction(movingDirection);

            // If there are no instrucitons between arm address and memory end
            // Return first element (from the beginning)
            if (closestInstr == null)
                return instructionList[0];

            // If found an element just return it
            else
                return closestInstr;
        }
    }
}
