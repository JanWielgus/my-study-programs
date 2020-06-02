using System;
using System.Collections.Generic;
using System.Text;

namespace SO_5_DistributedAlgorithmSimulator
{
	class SimulationConfig
	{
		// Singleton
		private static SimulationConfig instance = null;
		private SimulationConfig() // private constructor
		{

		}
		public static SimulationConfig getInstance()
		{
			if (instance == null)
				instance = new SimulationConfig();
			return instance;
		}


		// config values
		public readonly int amtOfProcessors_N = 50;
		public readonly float loadThreshold_p = 12;		// value between 0 and 100
		public readonly int maxDismissAttempts_z = 5;
		public readonly float askingThreshold_r = 10;		// value between 0 and 100

		// my config values
		public readonly float percentOfTasksToTransfer = 10;    // value between 0 and 100
		public readonly int averageTimeBetweenNewTask = 3;      // 3 time units average between new task is added
		public readonly int amtOfProcesses = 1000;

		public readonly int minProcessSize = 7;
		public readonly int maxProcessSize = 40; // assuming that maximum sized process can be computed in one cycle if use 100% load

		public readonly float minProcessLoad = 0.5f;
		public readonly float maxProcessLoad = 8;
	}
}
