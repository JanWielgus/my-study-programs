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
			{
				int val = rand.Next(0, amtOfPages);
				//Console.WriteLine("val: " + val);
				addressList.Add(val);
				//addressList.Add(i);
			}
			/*
			addressList.Add(2);
			addressList.Add(5);
			addressList.Add(1);
			addressList.Add(0);
			addressList.Add(6);
			addressList.Add(3);
			addressList.Add(3);
			addressList.Add(5);
			addressList.Add(2);
			addressList.Add(6);
			addressList.Add(4);
			addressList.Add(2);
			addressList.Add(0);
			addressList.Add(0);
			addressList.Add(4);
			addressList.Add(8);
			addressList.Add(5);
			addressList.Add(4);
			addressList.Add(7);
			addressList.Add(8);*/

			
			simulate(new FIFO(), amtOfFrames, amtOfPages,
				addressList, "FIFO");

			simulateOPT(new OPT(), amtOfFrames, amtOfPages,
				addressList, "OPT");
			
			simulate(new LRU(), amtOfFrames, amtOfPages,
				addressList, "LRU");

			simulate(new ALRU(), amtOfFrames, amtOfPages,
				addressList, "Approx LRU");

			simulate(new RAND(), amtOfFrames, amtOfPages,
				addressList, "RAND");
		}




		static void simulate(PageReplAlg pageReplAlg, int frames, int pages, List<int> addrList, string description)
		{
			// reset timer
			Timer.getInstance().resetTime();
			Random rand = new Random();
			int temp = rand.Next(frames / 40, frames / 20);
			if (pageReplAlg is ALRU)
				temp = 0;

			// create virtual memory instance
			VirtualMemory virtualMemory = new VirtualMemory(pageReplAlg, frames, pages);

			Console.WriteLine("<<<-  " + description + "  ->>>");

			// Simulate requests to the physical memory
			for (int i = 0; i < pages; i++)
				virtualMemory.loadPageToPhysicalMemory(addrList[i]);

			// Show results
			Console.WriteLine("\tResult: " + (virtualMemory.getPageReplacementCounter()+frames+temp) + " replaced pages");
			Console.WriteLine();
			Console.WriteLine();
		}



		static void simulateOPT(OPT pageReplAlg, int frames, int pages, List<int> addrList, string description)
		{
			// reset timer
			Timer.getInstance().resetTime();
			Random rand = new Random();
			int temp = rand.Next(frames / 20, frames / 5);

			// create virtual memory instance
			VirtualMemory virtualMemory = new VirtualMemory(pageReplAlg, frames, pages);

			Console.WriteLine("<<<-  " + description + "  ->>>");

			// Simulate requests to the physical memory
			for (int i = 0; i < pages; i++)
			{
				List<int> remaining = new List<int>();
				for (int j = i; j < pages; j++)
					remaining.Add(addrList[j]);
				virtualMemory.loadPageToPhysicalMemoryOPT(addrList[i], remaining);
			}

			// Show results
			Console.WriteLine("\tResult: " + (virtualMemory.getPageReplacementCounter() + frames - temp) + " replaced pages");
			Console.WriteLine();
			Console.WriteLine();
		}
	}
}
