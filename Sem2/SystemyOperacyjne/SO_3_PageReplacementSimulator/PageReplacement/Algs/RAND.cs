using System;
using System.Collections.Generic;
using System.Text;

namespace SO_3_PageReplacementSimulator.PageReplacement.Algs
{
	class RAND : PageReplAlg
	{
		public override void replacePage()
		{
			List<DataUnit> dataList = physicalMemory.getFrameArray();

			if (dataList.Count == 0)
				return;

			// find random element to delete
			Random rand = new Random();
			int toDeleteIndex = rand.Next(0, dataList.Count - 1);

			// Delete that random element
			physicalMemory.removeFrame(dataList[toDeleteIndex].Address);
		}
	}
}
