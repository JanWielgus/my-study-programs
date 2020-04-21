using SO_3_PageReplacementSimulator.PageReplacement.Algs;
using SO_3_PageReplacementSimulator.PageReplacement.MemoryUnits;
using System;
using System.Collections.Generic;
using System.Text;

namespace SO_3_PageReplacementSimulator.PageReplacement
{
	class VirtualMemory
	{
		private List<Page> pagesArray;
		private PhysicalMemory physicalMemory;
		private PageReplAlg pageReplAlg;

		private int pageReplacementCounter = 0;

		public VirtualMemory(PageReplAlg pageReplAlg, int amtOfFrames, int amtOfPages)
		{
			this.pageReplAlg = pageReplAlg;
			physicalMemory = new PhysicalMemory(amtOfFrames);
			pagesArray = new List<Page>();

			// fill array with instances
			for (int i = 0; i < amtOfPages; i++)
				pagesArray.Add(new Page(i));

			// give page repl alg pointer to the physical memory
			this.pageReplAlg.setPhysicalMemory(physicalMemory);
		}


		public bool loadPageToPhysicalMemory(int address)
		{
			// Update timer
			// Just increase time by some value
			Timer.getInstance().increaseTime(7);

			if (address >= pagesArray.Count || address < 0)
				return false;


			DataUnit toAdd = pagesArray[address]; // address is the same as index in array
			if (physicalMemory.addFrameToMemory(toAdd) == false)
			{
				pageReplacementCounter++;
				pageReplAlg.replacePage();

				physicalMemory.addFrameToMemory(toAdd);
			}

			return true;
		}


		public int getPageReplacementCounter()
		{
			return pageReplacementCounter;
		}
	}
}
