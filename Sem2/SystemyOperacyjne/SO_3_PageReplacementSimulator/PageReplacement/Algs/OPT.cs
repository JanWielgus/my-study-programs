using System;
using System.Collections.Generic;
using System.Text;

namespace SO_3_PageReplacementSimulator.PageReplacement.Algs
{
	class OPT : PageReplAlg
	{
		//List<int> occurances;
		List<int> allAddr;



		public override void replacePage()
		{
		}


		public void replacePageOPT(List<int> remainingRequests, int amtOfPages)
		{
			List<int> counts = new List<int>();
			for (int i = 0; i < amtOfPages; i++)
				counts.Add(0);

			for (int i = 0; i < remainingRequests.Count; i++)
				counts[remainingRequests[i]]++;

			int smallestIndex = -1;
			int temp = 0;
			while(smallestIndex == -1)
			{
				if (counts[temp] > 0)
					smallestIndex = temp;
				temp++;
			}


			for (int i=1; i<remainingRequests.Count; i++)
			{
				if (counts[i] < counts[smallestIndex] && counts[i] > 0)
					smallestIndex = i;
			}

			physicalMemory.removeFrame(smallestIndex);
		}
	}
}
