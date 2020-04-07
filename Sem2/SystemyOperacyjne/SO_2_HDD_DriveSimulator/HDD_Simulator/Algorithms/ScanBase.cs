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
            {
                instructionList.Add(instruction);
                return;
            }

            // Else add keeping the order (without sorting)


            // If new address is before the first instruction already in the list
            if (instruction.getAddress() < instructionList[0].getAddress())
            {
                instructionList.Insert(0, instruction);
                return;
            }


            // Find place between current instrucitons
            for (int i = 1; i < instructionList.Count; i++)
            {
                // If new address is between previous and next and break
                if (instruction.getAddress() >= instructionList[i - 1].getAddress() &&
                    instruction.getAddress() <= instructionList[i].getAddress())
                {
                    instructionList.Insert(i, instruction);
                    return;
                }
            }


            // If not found a place before, add at the end
            instructionList.Add(instruction);
        }



        protected Instruction getClosestInstruction(List<Instruction> list)
        {
            Instruction instr = getClosestInstruction(Direction.FORWARD, list);
            if (instr != null)
                return instr;
            else
                return getClosestInstruction(Direction.BACKWARD, list);
        }

        protected Instruction getClosestInstruction(Direction direction)
        {
            return getClosestInstruction(direction, instructionList);
        }


        protected Instruction getClosestInstruction(Direction direction, List<Instruction> list)
        {
            if (direction == Direction.FORWARD)
            {
                // Find first instruction with bigger address than current arm address
                foreach (Instruction instr in list)
                {
                    if (instr.getAddress() > driveArm.getCurrentAddress())
                        return instr;
                }
            }
            else if (direction == Direction.BACKWARD)
            {
                // Iterate backward
                // Find first instruction with less address than current arm address
                for (int i = list.Count - 1; i >= 0; i--)
                {
                    Instruction curInstr = list[i];
                    if (curInstr.getAddress() < driveArm.getCurrentAddress())
                        return curInstr;
                }
            }


            // if nothing was found - reached end of a memory address
            return null;
        }
    }

}
