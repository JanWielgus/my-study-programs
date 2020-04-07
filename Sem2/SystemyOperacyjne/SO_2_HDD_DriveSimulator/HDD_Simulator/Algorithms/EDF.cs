using System;
using System.Collections.Generic;
using System.Text;

/*
 * EDF - Earliest Deadline First
 * 
 * */

namespace SO_2_HDD_DriveSimulator.HDD_Simulator.Algorithms
{
    class EDF : SSTF
    {

        private List<Instruction> deadlineInstrList;


        public EDF()
        {
            deadlineInstrList = new List<Instruction>();
        }

        public override Instruction getNextInstruction()
        {
            if (deadlineInstrList.Count > 0)
            {
                int armAddr = DriveArm.getInstance().getCurrentAddress();

                Instruction result = deadlineInstrList[0];
                for (int i = 1; i < deadlineInstrList.Count; i++)
                    if (distance(armAddr, deadlineInstrList[i].getAddress()) <
                        distance(armAddr, result.getAddress()))
                        result = deadlineInstrList[i];

                return result;
            }
            else
                return base.getNextInstruction();
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


        private int distance(int one, int two)
        {
            return Math.Abs(one - two);
        }
    }
}
