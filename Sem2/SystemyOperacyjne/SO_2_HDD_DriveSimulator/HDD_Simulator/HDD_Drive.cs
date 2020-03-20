using System;
using System.Collections.Generic;
using System.Text;
using SO_2_HDD_DriveSimulator.HDD_Simulator.Algorithms;


/*
 * HDD Drive have an arm and have sum of the all movements
 * 
 * */


namespace SO_2_HDD_DriveSimulator.HDD_Simulator
{
    class HDD_Drive
    {
        private DriveArm driveArm;
        private SchedulingAlgorithm schedulingAlgorithm;
        private Sum armMovementSum;


        HDD_Drive(SchedulingAlgorithm schedulingAlgorithm)
        {
            driveArm = DriveArm.getInstance(); // Singleton
            this.schedulingAlgorithm = schedulingAlgorithm;
            armMovementSum = new Sum();
        }



        // Execute next read instruction
        // Returns false if there is instruction list is empty
        public bool executeNextInstruction()
        {
            if (schedulingAlgorithm.getAmtOfAwaitingInstructions() == 0)
                return false;

            // Get next instruction
            Instruction nextInstr = schedulingAlgorithm.getNextInstruction();

            // Calculate the address difference and add it to the total movement sum
            int addressDifference = Math.Abs(nextInstr.getAddress() - driveArm.getCurrentAddress());
            armMovementSum.add(addressDifference);

            // Move arm to the next instruction addresss
            driveArm.moveToAddress(nextInstr.getAddress());

            // Execute it
            nextInstr.execute();

            // Remove it from the list after execution
            schedulingAlgorithm.removeInstruction(nextInstr);

            // return false if instruction list is empty
            return schedulingAlgorithm.getAmtOfAwaitingInstructions() == 0 ? false : true;
        }


        public void addInstruciton(Instruction instrToAdd)
        {
            schedulingAlgorithm.addInstruction(instrToAdd);
        }


        public void addInstrucitonSequence(List<Instruction> instrListToAdd)
        {
            schedulingAlgorithm.addInstructionSequence(instrListToAdd);
        }


        public int getTotalArmMovingTime()
        {
            return armMovementSum.getSum();
        }
    }
}
