using System;
using System.Collections.Generic;
using System.Text;

namespace SO_2_HDD_DriveSimulator.HDD_Simulator.Algorithms
{
    abstract class ScanBase : SchedulingAlgorithm
    {
        protected enum Direction
        {
            FORWARD,
            BACKWARD
        }

        protected Direction movingDirection = Direction.FORWARD;



        // Adds new instructions in the ascending order (without sorting)
        public override void addInstruction(Instruction instruction)
        {
            // If there are no elements then add the first one
            if (instructionList.Count == 0)
                base.addInstruction(instruction); // just adds to the list

            // Else add keeping the order (without sorting)
            else
            {
                // If new address is before the first instruction already in the list
                if (instruction.getAddress() < instructionList[0].getAddress())
                    instructionList.Insert(0, instruction);

                // Find the proper place
                else
                {
                    for (int i = 1; i < instructionList.Count; i++)
                    {
                        // If new address is between previous and next and break
                        if (instruction.getAddress() >= instructionList[i - 1].getAddress() &&
                            instruction.getAddress() <= instructionList[i].getAddress())
                        {
                            instructionList.Insert(i, instruction);
                            break;
                        }
                    }
                }
            }

        }



        protected Instruction getClosestInstruction(Direction direction)
        {
            foreach (Instruction instr in instructionList)
            {
                if (direction == Direction.FORWARD)
                {
                    if (instr.getAddress() > driveArm.getCurrentAddress())
                        return instr;
                }
                else if (direction == Direction.BACKWARD)
                {
                    if (instr.getAddress() < driveArm.getCurrentAddress())
                        return instr;
                }
            }

            // if nothing was found - reached end of a memory address
            return null;
        }
    }

}
