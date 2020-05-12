using SO_4_FrameAssignmentSimulator.DataContainers;
using System;

namespace SO_4_FrameAssignmentSimulator
{
	class Program
	{
		static void Main(string[] args)
		{
			Console.WriteLine("Hello World!");

			Page page1 = new Page();
			Page page2 = new Page();

			Console.WriteLine("Page1: " + page1.getID() + " page 2: " + page2.getID());

		}
	}
}
