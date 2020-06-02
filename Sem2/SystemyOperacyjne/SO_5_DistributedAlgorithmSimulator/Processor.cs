using System;
using System.Collections.Generic;
using System.Text;

namespace SO_5_DistributedAlgorithmSimulator
{
	abstract class Processor
	{
		private List<Process> processList;
		private ProcessorManager processorManager;
		private SimulationConfig config = SimulationConfig.getInstance();


		public Processor(ProcessorManager processorManager)
		{
			processList = new List<Process>();
			this.processorManager = processorManager;
		}


		public float getCurrentLoad()
		{
			float load = 0;

			foreach (Process process in processList)
			{
				load += process.getLoadOnProcessor();
			}

			return load;
		}


		public abstract void addProcess(Process process);


		public void executeOnce()
		{
			foreach (Process process in processList)
			{
				float mult = process.getLoadOnProcessor() / 100f;
				process.compute((int)(config.maxProcessSize * mult));
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
