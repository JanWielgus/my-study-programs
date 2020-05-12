using SO_4_FrameAssignmentSimulator.DataContainers;
using System;
using System.Collections.Generic;
using System.Text;

namespace SO_4_FrameAssignmentSimulator
{
	class VirtualMemory
	{
		private RAM ram;
		private int pageFaultCounter;


		public VirtualMemory(RAM ram)
		{
			this.ram = ram;
			pageFaultCounter = 0;
		}


		public int getPageFaultCounter()
		{
			return pageFaultCounter;
		}


		public void simulateGetDataByPage(Page page)
		{
			throw new NotImplementedException();
			// TODO: asdfjaskldfj;alsdjf;laskjf;alsjf;alskdfj;alksdfj;aslkdfjas;ldkfja;slkfjdf   <<<<<<<



			page.increaseReferenceCounter();
		}
	}
}
