using SO_4_FrameAssignmentSimulator.DataContainers;
using System;
using System.Collections.Generic;
using System.Text;

namespace SO_4_FrameAssignmentSimulator
{
	class VirtualMemory
	{
		private RAM ram;
		private int pageFaultCounter;


		public VirtualMemory(RAM ram)
		{
			this.ram = ram;
			pageFaultCounter = 0;
		}


		public int getPageFaultCounter()
		{
			return pageFaultCounter;
		}


		// Return false if page failure occured (increase page counter in process)
		public bool simulateGetDataByPage(Page page)
		{
			bool returnValue = true;

			// Add page to the memory if it already is not
			if (!page.isInRam())
			{
				// If memory is full use LRU to remove one frame from memory
				if (ram.isFull())
				{
					// Increase counters
					pageFaultCounter++;
					returnValue = false;

					removeFrameFromRAM_LRU();
				}

				// Add page to the ram
				ram.addToMemory(page);
			}


			page.increaseReferenceCounter();
			return returnValue;
		}


		private void removeFrameFromRAM_LRU()
		{
			// !!!!!!!!!!!!!!!!!!!!!
			// 
			// Do przerobienia

			// Find least recently used page
			int lruFrameIdx = 0;
			List<Frame> ramFrameList = ram.getFrameList();
			for (int i = 1; i < ramFrameList.Count; i++)
			{
				if (ramFrameList[i].getReferenceCounter() > ramFrameList[lruFrameIdx].getReferenceCounter())
					lruFrameIdx = i;
			}

			ram.removeFrameByID(ramFrameList[lruFrameIdx].getID());
		}


		// Used by process if it is done
		public void removePageListFromMemory(List<Page> pageListToRemove)
		{
			foreach (Page pg in pageListToRemove)
			{
				if (pg.isInRam())
					ram.removeFrameByID(pg.getID());
			}
		}
	}
}
