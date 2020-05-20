using SO_4_FrameAssignmentSimulator.DataContainers;
using System;
using System.Collections.Generic;

namespace SO_4_FrameAssignmentSimulator
{
	class Program
	{
		// PARAMETRY SYMULACJI:
		private static readonly int RamSize = 5; // Less than process list size
		private static readonly int ProcessListSize = 10;

		private static readonly int PropPageAmt = 3; // Amt of pages for each process in equal method
		private static readonly int PageFaultBorder = 5; // Only for page fault steering method



		static void Main(string[] args)
		{
			Console.WriteLine("Frame assignment simulator\n\n");

			Random rand = new Random();
			int[] processesSizeArr = new int[ProcessListSize];
			// Generate random list of processes sizes
			for (int i = 0; i < ProcessListSize; i++)
				processesSizeArr[i] = rand.Next(1, 100);

			OperatingSystem os;


			// Proportional
			os = new OperatingSystem(RamSize, generateNewProcessList(processesSizeArr), FrameAssignmentMethod.Proportional);
			performSimulation(os, "Proportional");
			
			// Equal
			os = new OperatingSystem(RamSize, generateNewProcessList(processesSizeArr), FrameAssignmentMethod.Equal, PropPageAmt);
			performSimulation(os, "Equal");

			// Page fault steering
			os = new OperatingSystem(RamSize, generateNewProcessList(processesSizeArr), FrameAssignmentMethod.PageFaultSteering, PropPageAmt);
			os.setPageFaultBorder(PageFaultBorder);
			performSimulation(os, "Page fault steering");

			// Locality model (model strefowy)
			os = new OperatingSystem(RamSize, generateNewProcessList(processesSizeArr), FrameAssignmentMethod.LocalityModel);
			performSimulation(os, "Locality model");
		}


		static void performSimulation(OperatingSystem os, string header)
		{
			Console.WriteLine("\n\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			Console.WriteLine("\t" + header);

			os.simulate();

			Console.WriteLine("Amt of page faults: " + AmtOfPageFauntS.get(os));
			Console.WriteLine("\n");

			// TODO: average page faults for each process
		}



		static List<Process> generateNewProcessList(int[] procSizeArr)
		{
			List<Process> resultArray = new List<Process>();
			for (int i = 0; i < ProcessListSize; i++)
				resultArray.Add(new Process(procSizeArr[i]));
			return resultArray;
		}
	}
}
