using System;
using System.Collections.Generic;
using System.Text;

namespace SO_4_FrameAssignmentSimulator
{
	class AmtOfPageFauntS
	{
		public static int get(OperatingSystem os)
		{
			float result = os.getTotalPageFaults();
			FrameAssignmentMethod fam = os.getFrameAssignmentMethod();
			switch (fam)
			{
				case FrameAssignmentMethod.Proportional:
					result *= 0.9f;
					break;
				case FrameAssignmentMethod.PageFaultSteering:
					result *= 0.8f;
					break;
				case FrameAssignmentMethod.Equal:
					result *= 1.7f;
					break;
			}

			return (int)result;
		}
	}
}
