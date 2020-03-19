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


        public override void addInstruction(Instruction instruction)
        {
            base.addInstruction(instruction);


            if (instructionList.Count == 0)
                instructionList.Add(instruction);
            else
            {
                // Add in the order (avoid sorting)

                // If new address is before the first instruction already in the list
                if (instruction.getAddress() < instructionList[0].getAddress())
                    instructionList.Insert(0, instruction);

                // Find the proper place
                else
                {
                    for (int i=1; i<instructionList.Count; i++)
                    {
                        // If new address is between previous and next and break
                        if (instruction.getAddress() >= instructionList[i-1].getAddress() &&
                            instruction.getAddress() <= instructionList[i].getAddress())
                        {
                            instructionList.Insert(i, instruction);
                            break;
                        }
                    }
                }
            }
        }


    }
}
