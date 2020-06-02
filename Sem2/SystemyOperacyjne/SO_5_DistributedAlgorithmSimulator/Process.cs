using System;
using System.Collections.Generic;
using System.Text;

namespace SO_5_DistributedAlgorithmSimulator
{
	class Process
	{
		private static int lastID = 0;
		private readonly int processID;
		private readonly int processSize;
		private int processRemainingSize;
		private float loadOnProcessor;


		public Process(int processSize, float loadOnProcessor)
		{
			processID = lastID++;

			this.processSize = processSize;
			processRemainingSize = processSize;

			this.loadOnProcessor = loadOnProcessor;
		}

		public Process(Process toCopy)
		{
			this.processID = toCopy.processID;
			this.processSize = toCopy.processSize;
			this.processRemainingSize = toCopy.processRemainingSize;
			this.loadOnProcessor = toCopy.loadOnProcessor;
		}

		public void compute(int amountComputed)
		{
			processRemainingSize -= amountComputed;
		}


		public bool isDone()
		{
			return processRemainingSize <= 0;
		}


		// getters

		public int getID()
		{
			return processID;
		}

		public int getSize()
		{
			return processSize;
		}

		public int getRemainingSize()
		{
			return processRemainingSize;
		}

		public float getLoadOnProcessor()
		{
			return loadOnProcessor;
		}
	}
}
