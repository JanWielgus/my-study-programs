using System;
using System.Collections.Generic;
using System.Text;

namespace SO_3_PageReplacementSimulator.PageReplacement.Algs
{
	class FIFO : PageReplAlg
	{
		public override void replacePage()
		{
			List<DataUnit> dataList = physicalMemory.getFrameArray();

			if (dataList.Count == 0)
				return;

			// find frame to delete (with the smallest add to memory time stamp)
			DataUnit toDelete = dataList[0];
			for (int i=1; i<dataList.Count; i++)
			{
				if (dataList[i].MemoryAddTimeStamp < toDelete.MemoryAddTimeStamp)
					toDelete = dataList[i];
			}

			physicalMemory.removeFrame(toDelete.Address);
		}
	}
}
