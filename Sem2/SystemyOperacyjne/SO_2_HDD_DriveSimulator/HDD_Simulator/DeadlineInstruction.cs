using System;
using System.Collections.Generic;
using System.Text;

namespace SO_2_HDD_DriveSimulator.HDD_Simulator
{
    class DeadlineInstruction : Instruction
    {
        private int deadlineTime;

        public DeadlineInstruction(int deadlineTime)
        {
            this.deadlineTime = deadlineTime;
        }

        public int getDeadlineTime()
        {
            return deadlineTime;
        }
    }
}
