using SO_3_PageReplacementSimulator.PageReplacement;
using SO_3_PageReplacementSimulator.PageReplacement.Algs;
using System;
using System.Collections.Generic;

namespace SO_3_PageReplacementSimulator
{
	class Program
	{
		static void Main(string[] args)
		{
			Console.WriteLine("Page Replacement Simulator.");
			Console.WriteLine();

			string answer;
			Console.Write("How many pages: ");
			answer = Console.ReadLine();
			int amtOfPages = int.Parse(answer);

			Console.Write("How many frames: ");
			answer = Console.ReadLine();
			int amtOfFrames = int.Parse(answer);

			List<int> addressList = new List<int>();

			// Create array of addresses to request
			Random rand = new Random();
			for (int i = 0; i < amtOfPages; i++)
				addressList.Add(rand.Next(0, amtOfPages - 1));


			simulate(new FIFO(), amtOfFrames, amtOfPages,
				addressList, "FIFO");

			// OPT

			simulate(new LRU(), amtOfFrames, amtOfPages,
				addressList, "LRU");

			// Approx LRU

			simulate(new RAND(), amtOfFrames, amtOfPages,
				addressList, "RAND");

		}


		static void simulate(PageReplAlg pageReplAlg, int frames, int pages, List<int> addrList, string description)
		{
			// reset timer
			Timer.getInstance().resetTime();

			// create virtual memory instance
			VirtualMemory virtualMemory = new VirtualMemory(pageReplAlg, frames, pages);

			Console.WriteLine("<<<-  " + description + "  ->>>");

			// Simulate requests to the physical memory
			for (int i = 0; i < addrList.Count; i++)
				virtualMemory.loadPageToPhysicalMemory(addrList[i]);

			// Show results
			Console.WriteLine("\tResult: " + virtualMemory.getPageReplacementCounter() + " replaced pages");
			Console.WriteLine();
			Console.WriteLine();
		}
	}
}
