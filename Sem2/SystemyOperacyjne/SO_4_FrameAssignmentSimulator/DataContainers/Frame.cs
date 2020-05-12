using System;
using System.Collections.Generic;
using System.Text;

namespace SO_4_FrameAssignmentSimulator.DataContainers
{
	abstract class Frame
	{
		private static int nextID = 0;
		private static int getNextID()
		{
			nextID++;
			return nextID - 1;
		}


		private readonly int ID;
		private int referenceCounter;
		private bool isInRamFlag = false;

		public Frame()
		{
			ID = getNextID();
			referenceCounter = 0;
			isInRamFlag = false;
		}

		public int getID()
		{
			return ID;
		}

		public void increaseReferenceCounter()
		{
			referenceCounter++;
		}

		public int getReferenceCounter()
		{
			return referenceCounter;
		}


		public void setInRam(bool flag)
		{
			isInRamFlag = flag;
		}

		public bool isInRam()
		{
			return isInRamFlag;
		}
	}
}
