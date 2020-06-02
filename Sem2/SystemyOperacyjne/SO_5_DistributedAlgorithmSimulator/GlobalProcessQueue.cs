using System;
using System.Collections.Generic;
using System.Text;

namespace SO_5_DistributedAlgorithmSimulator
{
	class GlobalProcessQueue
	{
		private List<Process> processList;
		private List<int> processToAddTimeList;
		private GlobalClock clock = GlobalClock.getInstance();
		private SimulationConfig config = SimulationConfig.getInstance();


		public GlobalProcessQueue()
		{
			processList = new List<Process>();
			processToAddTimeList = new List<int>();
		}


		public void setProcessList(List<Process> processList)
		{
			this.processList = processList;
			this.processToAddTimeList.Clear();
			int randomUpperBound = processList.Count * config.averageTimeBetweenNewTask;
			Random rand = new Random();

			// fill the toAdd process time array
			for (int i = 0; i < processList.Count; i++)
				processToAddTimeList.Add(rand.Next(randomUpperBound));
		}


		public List<Process> getProcessesToCompute()
		{
			List<Process> returnList = new List<Process>();

			for (int i=0; i<processList.Count; i++)
			{
				if (processToAddTimeList[i] <= clock.getCurrentTime())
				{
					returnList.Add(processList[i]);

					// remove process and its toAdd time
					processList.RemoveAt(i);
					processToAddTimeList.RemoveAt(i);
					i--;
				}
			}

			return returnList;
		}


		public int getProcessesQueueSize()
		{
			return processList.Count;
		}
	}
}
