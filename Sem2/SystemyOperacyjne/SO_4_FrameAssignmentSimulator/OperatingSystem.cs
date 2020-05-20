using SO_4_FrameAssignmentSimulator.DataContainers;
using System;
using System.Collections.Generic;
using System.Text;

namespace SO_4_FrameAssignmentSimulator
{
	enum FrameAssignmentMethod
	{
		Proportional,
		Equal,
		PageFaultSteering,
		LocalityModel // model strefowy
	}

	class OperatingSystem
	{
		private RAM ram;
		private VirtualMemory virtualMemory;
		private List<Process> processList;
		private FrameAssignmentMethod frameAssignMethod;

		// for specific frame assignment methods
		private int pageFaultBorder = 10;


		public OperatingSystem(int ramSize, List<Process> processList, FrameAssignmentMethod method, int extra = -1)
		{
			ram = new RAM(ramSize);
			virtualMemory = new VirtualMemory(ram);
			frameAssignMethod = method;

			this.processList = processList;

			// setup all processes
			for (int i = 0; i < processList.Count; i++)
			{
				processList[i].setVirtualMemory(virtualMemory);

				// Create page list for process
				int processPageListSize = 5;
				switch (method)
				{
					case FrameAssignmentMethod.Proportional:
						processPageListSize = processList[i].getProcessSize() / 1;   // <<<<<<<<<<<<<<<<<<<   parametr do testowania, im więcej, tym mniej stron, ale proporcjonalnie
						if (processPageListSize < 1)
							processPageListSize = 1;
						break;
					case FrameAssignmentMethod.Equal:
					case FrameAssignmentMethod.PageFaultSteering:
						processPageListSize = extra == -1 ? 4 : extra;    // parametr do testowania <<<<
						break;
					case FrameAssignmentMethod.LocalityModel:
						processPageListSize = 10;
						break;
				}

				//Console.WriteLine("Ilosc ramek: " + processPageListSize);

				List<Page> newPageList = new List<Page>();
				for (int j = 0; j < processPageListSize; j++)
					newPageList.Add(new Page());

				processList[i].setPageList(newPageList);


				// Show process size
				//Console.WriteLine("Process " + i + " size: " + processList[i].getProcessSize());
			}

			
		}



		public void simulate()
		{
			while (processList.Count > 0)
			{
				// Execute each process in each interation
				for (int i=0; i<processList.Count; i++)
				{
					bool processExecResult;
					processExecResult = processList[i].execute(); // Process execution

					// Remove process if was fully executed
					if (processExecResult == false)
						processList.Remove(processList[i]);
					else
					{
						// Check actions for page fault steering
						if (frameAssignMethod == FrameAssignmentMethod.PageFaultSteering &&
							processList[i].getTempLocalPageFltCtr() >= pageFaultBorder)
						{
							// Add new page for this process
							List<Page> prevList = processList[i].getPageList();
							prevList.Add(new Page());
							processList[i].setPageList(prevList);
							processList[i].resetTempLocalPageFltCtr();
						}

						// Check actions for locality model
						else if (frameAssignMethod == FrameAssignmentMethod.LocalityModel)
						{
							// Find page with least references
							int leastRefIdx = 0;
							List<Page> pageList = processList[i].getPageList();
							for (int j = 1; j < pageList.Count; j++)
								if (pageList[j].getReferenceCounter() > pageList[leastRefIdx].getReferenceCounter())
									leastRefIdx = j;

							Page pgToRemove = pageList[leastRefIdx];
							if (pgToRemove.isInRam())
								ram.removeFrameByID(pgToRemove.getID());
						}
					}
				}

			}

		}




		// Only used when frame assignment method is pageFaultSteering
		public void setPageFaultBorder(int pageFaultBorder)
		{
			this.pageFaultBorder = pageFaultBorder;
		}



		public int getTotalPageFaults()
		{
			return virtualMemory.getPageFaultCounter();
		}


		public FrameAssignmentMethod getFrameAssignmentMethod()
		{
			return frameAssignMethod;
		}
	}
}
