using System;
using System.Collections.Generic;
using System.Text;


/*
 * Drive read instruction
 * */


namespace SO_2_HDD_DriveSimulator.HDD_Simulator
{
    class Instruction
    {
        private readonly int memLoc;

        private readonly int ID;
        private static int nextID_toSet = 0; // each instruction ID is set to this value and increment it


        // Random memory location
        Instruction()
        {
            Random rand = new Random();
            memLoc = rand.Next(Constants.FirstMemoryUnitAddr, Constants.LastMemoryUnitAddr);

            ID = nextID_toSet++;
        }


        // Set memory location
        Instruction(int memoryLocation)
        {
            memLoc = memoryLocation;

            ID = nextID_toSet++;
        }


        // Copy constructor
        Instruction(Instruction instrToCopy)
        {
            memLoc = instrToCopy.memLoc;
            ID = instrToCopy.ID;
        }


        void execute()
        {
            Console.WriteLine("Mem read instr no. " + ID + " addr: " + memLoc + " has just been executed.");
        }


        int getID()
        {
            return ID;
        }


        int getMemoryLocaiton()
        {
            return memLoc;
        }
    }
}
