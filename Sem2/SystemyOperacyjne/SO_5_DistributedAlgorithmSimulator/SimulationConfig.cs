using System;
using System.Collections.Generic;
using System.Text;

namespace SO_5_DistributedAlgorithmSimulator
{
	class SimulationConfig
	{
		// Singleton
		private SimulationConfig instance = null;
		private SimulationConfig() // private constructor
		{

		}
		public SimulationConfig getInstance()
		{
			if (instance == null)
				instance = new SimulationConfig();
			return instance;
		}


		// config values
		public readonly int amtOfProcessors_N = 50;
		public readonly float loadThreshold_p = 10;		// value between 0 and 100
		public readonly int maxDismissAttempts_z = 5;
		public readonly float askingThreshold = 15;		// value between 0 and 100

		// my config values
		public readonly float percentOfTasksToTransfer = 10;	// value between 0 and 100
	}
}
