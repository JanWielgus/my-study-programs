using System;
using System.Collections.Generic;
using System.Text;

namespace SO_3_PageReplacementSimulator.PageReplacement.Algs
{
	class ALRU : PageReplAlg
	{
		public override void replacePage()
		{
			List<DataUnit> dataList = physicalMemory.getFrameArray();

			if (dataList.Count == 0)
				return;

			bool foundFlag = false;
			DataUnit toDelete = dataList[0];
			while (foundFlag == false)
			{
				// find frame to delete (with the smallest last use time stamp)
				toDelete = dataList[0];
				for (int i = 1; i < dataList.Count; i++)
				{
					if (dataList[i].LastUseTimeStamp < toDelete.LastUseTimeStamp)
						toDelete = dataList[i];
				}

				toDelete.Chance--;
				toDelete.LastUseTimeStamp = Timer.getInstance().getTime();
				if (toDelete.Chance == 0)
					foundFlag = true;
			}

			physicalMemory.removeFrame(toDelete.Address);
		}
	}
}
