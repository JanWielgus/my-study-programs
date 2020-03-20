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
        private readonly int address;
        private readonly int ID;

        private static int nextID_toSet = 0; // each instruction ID is set to this value and increment it


        // Random memory location
        public Instruction()
        {
            Random rand = new Random();
            address = rand.Next(Constants.FirstMemoryUnitAddr, Constants.LastMemoryUnitAddr);

            ID = nextID_toSet++;
        }


        // Set memory location
        public Instruction(int memoryLocation)
        {
            address = memoryLocation;

            ID = nextID_toSet++;
        }


        // Copy constructor
        public Instruction(Instruction instrToCopy)
        {
            address = instrToCopy.address;
            ID = instrToCopy.ID;
        }


        public void execute()
        {
            Console.WriteLine("Mem read instr no. " + ID + " addr: " + address + " has just been executed.");
        }


        public int getID()
        {
            return ID;
        }


        public int getAddress()
        {
            return address;
        }
    }
}
