using System;
using System.Collections.Generic;
using System.Text;

namespace SO_3_PageReplacementSimulator.PageReplacement
{
	abstract class DataUnit
	{
		public int Address { get; set; }
		public bool InMemory { get; set; }
		public int MemoryAddTimeStamp { get; set; }
		public int LastUseTimeStamp { get; set; }
		public int Chance { get; set; } // only for ALRU

		public DataUnit(int addr)
		{
			Address = addr;
			InMemory = false;
			MemoryAddTimeStamp = 0;
			LastUseTimeStamp = 0;
			Chance = 1;
		}
	}
}
