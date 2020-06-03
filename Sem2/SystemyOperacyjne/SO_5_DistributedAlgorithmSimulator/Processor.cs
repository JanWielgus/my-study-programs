using System;
using System.Collections.Generic;
using System.Text;

namespace SO_5_DistributedAlgorithmSimulator
{
	abstract class Processor
	{
		protected List<Process> processList;
		protected ProcessorManager processorManager;
		protected SimulationConfig config = SimulationConfig.getInstance();
		protected SimulationStats stats = SimulationStats.getInstance();


		public Processor(ProcessorManager processorManager)
		{
			processList = new List<Process>();
			this.processorManager = processorManager;
		}


		public float getCurrentLoad()
		{
			float load = 0;

			for (int i=0; i<processList.Count; i++)
				load += processList[i].getLoadOnProcessor();

			return load;
		}


		public abstract void addProcess(Process process);


		public void forceAddProcess(Process processs)
		{
			processList.Add(processs);
		}


		public void executeOnce()
		{
			for (int i=0; i<processList.Count; i++)
			{
				Process curProcess = processList[i];

				// compute process
				float mult = curProcess.getLoadOnProcessor() / 100f;
				curProcess.compute((int)(100 * mult + 0.5));

				// Remove if fully computed
				if (curProcess.isDone())
				{
					processList.RemoveAt(i);
					i--;
				}
			}
		}


		// Return some processes to processor that requested
		public List<Process> transferSomeProcesses()
		{
			int amtToTransfer = (int)((config.percentOfTasksToTransfer / 100.0f) * (float)processList.Count + 0.5f);
			List<Process> toTransfer = new List<Process>();

			for (int i=0; i<amtToTransfer; i++)
			{
				toTransfer.Add(processList[0]);
				processList.RemoveAt(0);
			}

			return toTransfer;
		}
	}
}
