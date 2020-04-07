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
            DeadlineInstruction closestDeadline = (DeadlineInstruction)getClosestInstruction(direction, deadlineInstrList);

            if (closestDeadline == null)
                return closestNormal;
            if (closestNormal == null)
                return closestDeadline;

            if (direction == Direction.FORWARD)
            {
                if (closestDeadline.getAddress() < closestNormal.getAddress())
                    return closestDeadline;
                else
                    return closestNormal;
            }
            else // Backward
            {
                if (closestDeadline.getAddress() > closestNormal.getAddress())
                    return closestDeadline;
                else
                    return closestNormal;
            }
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

            // Get closest instructions in both directions
            Instruction closestForward = getClosestInstruction(Direction.FORWARD, deadlineInstrList);
            Instruction closestBackward = getClosestInstruction(Direction.BACKWARD, deadlineInstrList);

            // If one of them is null, return the other one
            if (closestForward == null)
                return Direction.BACKWARD;
            if (closestBackward == null)
                return Direction.FORWARD;

            if (distance(armAddr, closestForward.getAddress()) < distance(armAddr, closestBackward.getAddress()))
                return Direction.FORWARD;
            else
                return Direction.BACKWARD;
        }

        private int distance(int one, int two)
        {
            return Math.Abs(one - two);
        }
    }
}
