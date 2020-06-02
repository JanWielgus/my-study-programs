using SO_5_DistributedAlgorithmSimulator.ConcreteProcessors;
using System;
using System.Collections.Generic;

namespace SO_5_DistributedAlgorithmSimulator
{
	class Program
	{
		static List<Process> processList;
		static Random rand;

		enum ProcType
		{
			Type1,
			Type2,
			Type3
		}

		static void Main(string[] args)
		{
			rand = new Random();

			// fill processList array
			generateProcessList();


			simulate(ProcType.Type1);

			simulate(ProcType.Type2);

			simulate(ProcType.Type3);
		}


		static void generateProcessList()
		{
			SimulationConfig config = SimulationConfig.getInstance();
			processList = new List<Process>();

			for (int i=0; i<config.amtOfProcesses; i++)
			{
				int size = rand.Next(config.minProcessSize, config.maxProcessSize);
				float load = randFloat(config.minProcessLoad, config.maxProcessLoad);

				processList.Add(new Process(size, load));
			}
		}


		static List<Process> getProcessListCopy()
		{
			List<Process> copy = new List<Process>();
			foreach (Process proc in processList)
				copy.Add(new Process(proc));
			return copy;
		}


		static void simulate(ProcType type)
		{
			SimulationStats stats = SimulationStats.getInstance();
			stats.reset();

			SimulationConfig config = SimulationConfig.getInstance();

			List<Processor> processorList = new List<Processor>();

			GlobalProcessQueue processQueue = new GlobalProcessQueue();
			processQueue.setProcessList(getProcessListCopy());

			ProcessorManager procManager = new ProcessorManager(processQueue);


			// Generate processors
			for (int i=0; i<config.amtOfProcessors_N; i++)
			{
				if (type == ProcType.Type1)
					processorList.Add(new ProcessorType1(procManager));
				else if (type == ProcType.Type2)
					processorList.Add(new ProcessorType2(procManager));
				else // if (type == ProcType.Type3)
					processorList.Add(new ProcessorType3(procManager));
			}

			procManager.setProcessorList(processorList);




			while (processQueue.getProcessesQueueSize() > 0)
			{
				procManager.executeProcessorsOnce();
			}


			// Show results
			Console.WriteLine();
			Console.WriteLine();
			if (type == ProcType.Type1)
				Console.WriteLine("Processor Type 1");
			else if (type == ProcType.Type2)
				Console.WriteLine("Processor Type 2");
			else // if (type == ProcType.Type3)
				Console.WriteLine("Processor Type 3");

			Console.WriteLine("\tAverage processor load: " + stats.getAveragePorcessorLoading());
			Console.WriteLine("\tAverage processor load variation: " + stats.getAverageLoadVariation());
			Console.WriteLine("\tAmount of load requests and migration: " + stats.getAmtOfProcessorQueries());
		}



		static float randFloat(float min, float max)
		{
			float result = (float)rand.NextDouble() * (max - min);
			result += min;
			return result;
		}
	}
}
