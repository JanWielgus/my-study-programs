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
            if (closestInstr != null)
                return closestInstr;
            else
            {
                // Change direction
                movingDirection = movingDirection == Direction.FORWARD ? Direction.BACKWARD : Direction.FORWARD;

                closestInstr = getClosestInstruction(movingDirection);

                // If backwards closest instruction is also null then something is wrong so throw an exception
                if (closestInstr != null)
                    return closestInstr;
                else
                    // If this method is wrote properly, this exception should never occur
                    throw new InstructionNotFoundException("There are no new instructions in both ways");
            }
        }


        public class InstructionNotFoundException : Exception
        {
            public InstructionNotFoundException() { }
            public InstructionNotFoundException(string message) : base(message) { }
            public InstructionNotFoundException(string message, Exception inner) : base(message, inner) { }
            protected InstructionNotFoundException(
              System.Runtime.Serialization.SerializationInfo info,
              System.Runtime.Serialization.StreamingContext context) : base(info, context) { }
        }
    }
}
