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


        protected Instruction getClosestInstruction(Direction direction)
        {
            foreach (Instruction instr in instructionList)
            {
                if (direction == Direction.FORWARD)
                {
                    if (instr.getAddress() >= driveArm.getCurrentAddress())
                        return instr;
                }
                else if (direction == Direction.BACKWARD)
                {
                    if (instr.getAddress() <= driveArm.getCurrentAddress())
                        return instr;
                }
            }

            // if nothing was found - reached end of a memory address
            return null;
        }
    }

}
