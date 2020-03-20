using System;
using System.Collections.Generic;
using SO_2_HDD_DriveSimulator.HDD_Simulator;
using SO_2_HDD_DriveSimulator.HDD_Simulator.Algorithms;


/*
 * This simulation is very simple
 * For example do not include disc arm movement
 * Time to reach memory address is proportional to the difference between addresses
 * */

namespace SO_2_HDD_DriveSimulator
{
    class Program
    {
        private const int AmtOfSequences = 3;


        static void Main(string[] args)
        {
            Console.WriteLine("Hello World!");

            // Create the first instruciton sequence
            List<List<Instruction>> instrSeq = createInstrucitonSequence();

            
            runDrive(new HDD_Drive(new FCFS()),
                getInstructionSequenceCopy(instrSeq),
                "Drive 1 - FCFS");


            runDrive(new HDD_Drive(new SSTF()),
                getInstructionSequenceCopy(instrSeq),
                "Drive 2 - SSTF");


            runDrive(new HDD_Drive(new SCAN()),
                getInstructionSequenceCopy(instrSeq),
                "Drive 3 - SCAN");
            

            runDrive(new HDD_Drive(new C_SCAN()),
                getInstructionSequenceCopy(instrSeq),
                "Drive 4 - C-SCAN");
        }


        static List<List<Instruction>> createInstrucitonSequence()
        {
            List<List<Instruction>> generatedSeq = new List<List<Instruction>>();
            Random random = new Random();

            for (int i=0; i<AmtOfSequences; i++)
            {
                List<Instruction> newList = new List<Instruction>();
                generatedSeq.Add(newList);

                int amtOfInstructionsInList = random.Next(10, 40);

                for (int j = 0; j < amtOfInstructionsInList; j++)
                    newList.Add(new Instruction()); // add new read instruction with the random address
            }

            return generatedSeq;
        }


        static void runDrive(HDD_Drive hdd_drive, List<List<Instruction>> instrSeq, string text)
        {
            Console.WriteLine("   >>>>><    " + text + "    ><<<<<");
            Console.WriteLine();

            // Set the drive arm starting memory address
            DriveArm.getInstance().moveToAddress((Constants.FirstMemoryUnitAddr + Constants.LastMemoryUnitAddr)/2);

            int count = 0;
            hdd_drive.addInstrucitonList(instrSeq[0]);

            int arrayIndex = 1; // index of next process list to add
            while (hdd_drive.executeNextInstruction())
            {
                count++;

                // Add next instruction list every 8th processor execution
                if (arrayIndex < AmtOfSequences && count % 8 == 0)
                    hdd_drive.addInstrucitonList(instrSeq[arrayIndex++]);
            }


            // Show average turnaound time
            Console.WriteLine();
            Console.WriteLine("Total arm moving time: " + hdd_drive.getTotalArmMovingTime());
            Console.WriteLine("<<<<<   ENDED");
            Console.WriteLine();
            Console.WriteLine();
        }





        static List<List<Instruction>> getInstructionSequenceCopy(List<List<Instruction>> toCopy)
        {
            List<List<Instruction>> copy = new List<List<Instruction>>();

            for (int i = 0; i < toCopy.Count; i++)
                copy.Add(getInstructionsListCopy(toCopy[i]));

            return copy;
        }

        static List<Instruction> getInstructionsListCopy(List<Instruction> listToCopy)
        {
            List<Instruction> copy = new List<Instruction>();

            for (int i = 0; i < listToCopy.Count; i++)
                copy.Add(getInstructionCopy(listToCopy[i]));

            return copy;
        }

        static Instruction getInstructionCopy(Instruction processToCopy)
        {
            return new Instruction(processToCopy);
        }
    }

}
