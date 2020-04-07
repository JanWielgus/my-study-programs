using System;
using System.Collections.Generic;
using System.Text;

/*
 * Feasible deadline SCAN
 * 
 * */

namespace SO_2_HDD_DriveSimulator.HDD_Simulator.Algorithms
{
    class FD_SCAN : ScanBase
    {
        private List<Instruction> deadlineInstrList;


        public FD_SCAN()
        {
            deadlineInstrList = new List<Instruction>();
        }

        public override Instruction getNextInstruction()
        {
            Direction direction = determineDirection();
            Instruction closestNormal = getClosestInstruction(direction);

            if (deadlineInstrList.Count == 0)
                return instructionList[0];

            DeadlineInstruction closestDeadline = (DeadlineInstruction)getClosestInstruction(direction, deadlineInstrList);

            Instruction toReturn;

            if (closestDeadline == null)
                toReturn = closestNormal;
            if (closestNormal == null)
                toReturn = closestDeadline;

            if (direction == Direction.FORWARD)
            {
                if (closestDeadline.getAddress() < closestNormal.getAddress())
                    toReturn = closestDeadline;
                else
                    toReturn = closestNormal;
            }
            else // Backward
            {
                if (closestDeadline.getAddress() > closestNormal.getAddress())
                    toReturn = closestDeadline;
                else
                    toReturn = closestNormal;
            }

            if (toReturn == null)
            {
                if (deadlineInstrList.Count > 0)
                    return deadlineInstrList[0];
                else
                    return instructionList[0];
            }
            else
                return toReturn;
        }

        public override int getAmtOfAwaitingInstructions()
        {
            return base.getAmtOfAwaitingInstructions() + deadlineInstrList.Count;
        }

        public override void addInstruction(Instruction instruction)
        {
            if (instruction is DeadlineInstruction)
                deadlineInstrList.Add(instruction);
            else
                base.addInstruction(instruction);
        }

        public override bool removeInstruction(Instruction instrToRemove)
        {
            if (instrToRemove is DeadlineInstruction)
                return deadlineInstrList.Remove(instrToRemove);
            else
                return base.removeInstruction(instrToRemove);
        }


        private Direction determineDirection()
        {
            // Check in which way is the closest deadline instruciton

            int armAddr = DriveArm.getInstance().getCurrentAddress();

            Instruction closest;

            if (deadlineInstrList.Count > 0)
                closest = getClosestInstruction(deadlineInstrList);
            else
                closest = getClosestInstruction(instructionList);


            if (closest.getAddress() >= armAddr)
                return Direction.FORWARD;
            else
                return Direction.BACKWARD;
        }

    }
}
