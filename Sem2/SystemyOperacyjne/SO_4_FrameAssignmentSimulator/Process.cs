using SO_4_FrameAssignmentSimulator.DataContainers;
using System;
using System.Collections.Generic;
using System.Text;

namespace SO_4_FrameAssignmentSimulator
{
	class Process
	{
		private readonly int ProcessSize;
		private int remainingMemoryAccesses;
		private List<Page> localPageList = null;
		private int lastCalledPageIdx;
		private int localPageFaultCounter;
		private VirtualMemory virtualMemory;
		private Random rand;


		public Process(int processSize)
		{
			ProcessSize = processSize;
			remainingMemoryAccesses = ProcessSize;
			localPageFaultCounter = 0;

			rand = new Random();
		}

		public void setVirtualMemory(VirtualMemory vm)
		{
			this.virtualMemory = vm;
		}


		public void setPageList(List<Page> pageList)
		{
			this.localPageList = pageList;
			lastCalledPageIdx = 0;
		}

		public List<Page> getPageList()
		{
			return localPageList;
		}


		public int getRemainingMemoryAccessesCount()
		{
			return remainingMemoryAccesses;
		}


		// Return false, if process was fully executed and can be removed
		public bool execute()
		{
			// Something is wrong if calling this process when it has been fully executed
			if (localPageList.Count == 0)
				throw new Exception("Cos jest nie tak. To zadanie juz zostalo wykonane");


			// Prepare page to access
			Page pageToAccess;
			int randNum = rand.Next(0, 1);
			// 50% chance to use last page again
			if (randNum == 0)
				pageToAccess = localPageList[lastCalledPageIdx];
			else
			{
				randNum = rand.Next(0, localPageList.Count - 1);
				pageToAccess = localPageList[randNum];
				lastCalledPageIdx = randNum;
			}


			// Simulate memory access
			bool memResult = virtualMemory.simulateGetDataByPage(pageToAccess);
			if (memResult == false)
				localPageFaultCounter++;

			// Decrease remaining memory accesses
			remainingMemoryAccesses--;

			// If this task was fulle executed
			if (remainingMemoryAccesses == 0)
			{
				// Remove all pages from ram
				virtualMemory.removePageListFromMemory(localPageList);
				return false;
			}

			return true;
		}


		public int getLocalPageFaultCounter()
		{
			return localPageFaultCounter;
		}
	}
}
