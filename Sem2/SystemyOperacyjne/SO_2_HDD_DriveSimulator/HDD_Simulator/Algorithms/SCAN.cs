using System;
using System.Collections.Generic;
using System.Text;

/*
 * SCAN - Algorithm works like elevator (elevetor algotirhm)
 * Drive arm moves in one direction executing instructions on its path.
 * When reaches the end it begin to scan in the opposite way.
 * */

namespace SO_2_HDD_DriveSimulator.HDD_Simulator.Algorithms
{
    class SCAN : ScanBase
    {
        public override Instruction getNextInstruction()
        {
            // If there are any tasks throw an exception
            if (instructionList.Count == 0)
                throw new IndexOutOfRangeException("Instruction list is empty");


            Instruction closestInstr = getClosestInstruction(movingDirection);

            // If result is null then change the direction and try again
            if (closestInstr == null)
            {
                // Change direction
                movingDirection = movingDirection == Direction.FORWARD ? Direction.BACKWARD : Direction.FORWARD;

                return getClosestInstruction(movingDirection);
            }
            else
                return closestInstr;
        }
    }
}
