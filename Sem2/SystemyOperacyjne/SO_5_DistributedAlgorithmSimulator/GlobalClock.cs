using System;
using System.Collections.Generic;
using System.Text;

namespace SO_5_DistributedAlgorithmSimulator
{
	class GlobalClock
	{
		// singleton
		private static GlobalClock instance = null;
		public static GlobalClock getInstance()
		{
			if (instance == null)
				instance = new GlobalClock();
			return instance;
		}
		private GlobalClock()
		{
			reset();
		}


		// other componemets
		private uint currentTime;
	

		public uint getCurrentTime()
		{
			return currentTime;
		}

		public void tick()
		{
			currentTime++;
		}

		public void reset()
		{
			currentTime = 0;
		}
	}
}
