using SO_4_FrameAssignmentSimulator.DataContainers;
using System;
using System.Collections.Generic;
using System.Text;

namespace SO_4_FrameAssignmentSimulator
{
	class RAM
	{
		private readonly int MemorySize;
		private List<Frame> frameList;


		public RAM(int memSize)
		{
			MemorySize = memSize;
			frameList = new List<Frame>();
		}


		// return false if page was already in memory
		// throws exception if memory is full
		// return true if page was successfully added to the physical memory
		public bool addToMemory(Page page)
		{
			// throw exception if memory is full
			if (isFull())
				throw new Exception("Nie mozna dodac ramki do pelnej pamieci");

			// check if this page is already in memory
			// and return if was
			foreach (Frame fr in frameList)
			{
				// Break if this page (frame) was already in memory
				if (fr.getID() == page.getID())
					return false;
			}


			// add page to the frame list
			frameList.Add(page);
			page.setInRam(true);
			return true;
		}


		public void removeFrameByID(int ID)
		{
			if (isEmpty())
				throw new Exception("Nie mozna usunac ramki z pustej pamieci");


			// find frame with such ID
			foreach (Frame fr in frameList)
			{
				if (fr.getID() == ID)
				{
					fr.setInRam(false);
					frameList.Remove(fr);
					return;
				}
			}


			// If program is here, ID was not found
			throw new Exception("Nie ma w pamieci ramki z takim ID");
		}


		public List<Frame> getFrameList()
		{
			return frameList;
		}



		public int getMemorySize()
		{
			return MemorySize;
		}

		public int getFramesInMemoryAmt()
		{
			return frameList.Count;
		}

		public int getFreeFramesAmt()
		{
			return getMemorySize() - getFramesInMemoryAmt();
		}

		public bool isFull()
		{
			return getFreeFramesAmt() == 0;
		}

		public bool isEmpty()
		{
			return getFramesInMemoryAmt() == 0;
		}
	}
}
