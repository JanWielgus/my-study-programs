using System;
using System.Collections.Generic;
using System.Text;

namespace SO_5_DistributedAlgorithmSimulator
{
	class ProcessorManager
	{
		private List<Processor> processorList;
		private GlobalProcessQueue processQueue;
		private GlobalClock clock = GlobalClock.getInstance();
		private SimulationStats stats = SimulationStats.getInstance();
		private Random rand;
		private int nextProcessorToGetNewProcess = 0;

		
		public ProcessorManager(GlobalProcessQueue processQueue)
		{
			processorList = new List<Processor>();
			this.processQueue = processQueue;
			rand = new Random();
		}


		public void setProcessorList(List<Processor> processorList)
		{
			this.processorList = new List<Processor>(processorList);
		}


		public Processor getRandomProcessor()
		{
			int index = rand.Next() % processorList.Count;
			return processorList[index];
		}


		public void executeProcessorsOnce()
		{
			// Check if there are some new tasks
			List<Process> newProcessList = processQueue.getProcessesToCompute();
			while (newProcessList.Count > 0)
			{
				processorList[nextProcessorToGetNewProcess].addProcess(newProcessList[0]);
				newProcessList.RemoveAt(0);

				nextProcessorToGetNewProcess++;
				nextProcessorToGetNewProcess %= processorList.Count;
			}


			// Execute processors
			foreach (Processor proc in processorList)
			{
				// execute processor once
				proc.executeOnce();

				// update stats
				stats.addNewProcesssorLoad(proc.getCurrentLoad());
			}
				


			// Tick a clock
			clock.tick();
		}
	}
}
