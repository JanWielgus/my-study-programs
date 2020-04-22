using System;
using System.Collections.Generic;
using System.Text;

namespace SO_3_PageReplacementSimulator.PageReplacement
{
	class PhysicalMemory
	{
		private int Size; // size of physical memory
		private List<DataUnit> frameArray;


		public PhysicalMemory(int size)
		{
			Size = size;
			frameArray = new List<DataUnit>();
		}


		public bool addFrameToMemory(DataUnit frameToAdd)
		{
			//Timer.getInstance().increaseTime(3);
			// Check if frame of this address already exist in memory array
			for (int i=0; i<frameArray.Count; i++)
			{
				if (frameArray[i].Address == frameToAdd.Address)
				{
					frameArray[i].LastUseTimeStamp = Timer.getInstance().getTime();
					return true;
				}
			}

			// Check if there is place for new frame
			if (frameArray.Count >= Size)
				return false;

			// Add new frame
			frameToAdd.InMemory = true;
			Timer timer = Timer.getInstance();
			frameToAdd.LastUseTimeStamp = timer.getTime();
			frameToAdd.MemoryAddTimeStamp = timer.getTime();
			frameToAdd.Chance = 1;
			frameArray.Add(frameToAdd);
			//Console.WriteLine("\tAdded frame " + frameToAdd.Address + "\t" + frameArray.ToArray());

			return true;
		}


		public bool removeFrame(int address)
		{
			for (int i=0; i<frameArray.Count; i++)
			{
				DataUnit currentFrame = frameArray[i];
				//Console.WriteLine("\t\tRemoving frame " + currentFrame.Address);

				if (currentFrame.Address == address)
				{
					currentFrame.InMemory = false;
					frameArray.RemoveAt(i);
					return true;
				}
			}

			return false;
		}

		
		public List<DataUnit> getFrameArray()
		{
			return frameArray;
		}


		public bool isFull()
		{
			return frameArray.Count == Size;
		}
	}
}
