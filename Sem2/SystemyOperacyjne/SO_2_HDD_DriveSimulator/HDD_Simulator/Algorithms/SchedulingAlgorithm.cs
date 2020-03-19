﻿using System;
using System.Collections.Generic;
using System.Text;

namespace SO_2_HDD_DriveSimulator.HDD_Simulator.Algorithms
{
    abstract class SchedulingAlgorithm
    {
        protected List<Instruction> instructionList;



        public SchedulingAlgorithm()
        {
            instructionList = new List<Instruction>();
        }


        // Return instruction that have to be executed next
        // Each specific scheduling algorithm implement its own method
        public abstract Instruction getNextInstruction();


        // Add new scheduling algorithm to the list
        public virtual void addInstruction(Instruction instruction)
        {
            throw new NotImplementedException();
        }


        // Add an instruction sequence (in a list)
        public void addInstructionSequence(List<Instruction> instructionListToAdd)
        {
            foreach (Instruction instr in instructionListToAdd)
                addInstruction(instr);
        }


        // Execute instruction and remove it from the list
        public virtual bool removeInstruction(Instruction instrToRemove)
        {
            return instructionList.Remove(instrToRemove);
        }


        public int getAmtOfAwaitingInstructions()
        {
            return instructionList.Count;
        }

    }
}
