using System;
using System.Collections.Generic;
using System.Text;

namespace SO_3_PageReplacementSimulator.PageReplacement
{
	class Timer
	{
		private static Timer timerInstance = null;

		// Singleton
		public static Timer getInstance()
		{
			if (timerInstance == null)
				timerInstance = new Timer();
			return timerInstance;
		}


		private int time;

		private Timer()
		{
			resetTime();
		}


		public void increaseTime(int timeToAdd)
		{
			time += timeToAdd;
		}

		public int getTime()
		{
			return time;
		}

		public void resetTime()
		{
			time = 0;
		}
	}
}
