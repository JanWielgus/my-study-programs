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


		public void executeOnce()
		{
			for (int i=0; i<processList.Count; i++)
			{
				Process curProc = processList[i];

				// compute process
				float mult = curProc.getLoadOnProcessor() / 100f;
				curProc.compute((int)(config.maxProcessSize * mult));

				// Remove if fully computed
				if (curProc.isDone())
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
