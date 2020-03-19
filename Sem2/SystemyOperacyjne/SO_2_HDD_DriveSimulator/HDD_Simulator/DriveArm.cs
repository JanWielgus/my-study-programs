using System;
using System.Collections.Generic;
using System.Text;

/*
 * Drive arm class
 * 
 * Implemented using Sngleton design pattern
 * */



namespace SO_2_HDD_DriveSimulator.HDD_Simulator
{
    class DriveArm
    {
        private int currentAddress = 0;


    // >> Singleton part

        // The only instance
        private static DriveArm driveArmInstance;
        private static bool isCreated = false;

        public static DriveArm getInstance()
        {
            if (!isCreated)
                driveArmInstance = new DriveArm();

            return driveArmInstance;
        }


        // Private constructor (used only inside the class)
        private DriveArm()
        {
            currentAddress = 0;
        }

    // << End of the Singleton part



        // Move drive arm to the given address
        public bool moveToAddress(int address)
        {
            if (!inAddressRange(address))
                return false;

            currentAddress = address;
            return true;
        }


        // Get address that arm is currently on
        public int getCurrentAddress()
        {
            return currentAddress;
        }



        // Checks if address is in the memory address range
        private bool inAddressRange(int address)
        {
            if (address >= Constants.FirstMemoryUnitAddr && address <= Constants.LastMemoryUnitAddr)
                return true;
            return false;
        }
    }
}
