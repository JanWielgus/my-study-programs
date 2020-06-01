using System;
using System.Collections.Generic;
using System.Text;

namespace SO_5_DistributedAlgorithmSimulator
{
	class Average
	{
		private double sum;
		private uint amount;

		public Average()
		{
			reset();
		}


		public void addNewValue(double newValue)
		{
			amount++;
			sum += newValue;
		}


		public double getAverage()
		{
			if (amount > 0)
				return sum / amount;
			else
				return 0;
		}


		public void reset()
		{
			sum = 0;
			amount = 0;
		}
	}
}
