using System;
using System.Collections.Generic;
using System.Text;

namespace SO_3_PageReplacementSimulator.PageReplacement.Algs
{
	abstract class PageReplAlg
	{
		protected PhysicalMemory physicalMemory = null;
		
		public void setPhysicalMemory(PhysicalMemory physicalMemory)
		{
			this.physicalMemory = physicalMemory;
		}

		public abstract void replacePage();
	}
}
