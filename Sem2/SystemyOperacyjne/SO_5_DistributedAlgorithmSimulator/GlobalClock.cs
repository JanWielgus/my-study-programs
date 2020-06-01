using System;
using System.Collections.Generic;
using System.Text;

namespace SO_5_DistributedAlgorithmSimulator
{
	class GlobalClock
	{
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
