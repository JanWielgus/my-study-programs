using System;
using System.Collections.Generic;
using System.Text;

/*
	Na procesorze x pojawia sie zadanie. Nastepnie:

	2.Jesli obciążenie x przekracza wartość progową p,
	proces zostaje wysłany na losowo wybrany procesor y o obciążeniu mniejszym od p
	(jeśli wylosowany y ma obc.>p, losowanie powtarza się do skutku).
	Jeśli nie przekracza - proces wykonuje się na x.
*/



namespace SO_5_DistributedAlgorithmSimulator.ConcreteProcessors
{
	class ProcessorType2 : Processor
	{
		public ProcessorType2(ProcessorManager processorManager) : base(processorManager)
		{
		}


		public override void addProcess(Process process)
		{
			if (getCurrentLoad() > config.loadThreshold_p)
			{
				// Find first processor with load below p
				for (int i=0; i<config.amtOfProcessors_N; i++)
				{
					Processor randProc = processorManager.getRandomProcessor();

					// update stats
					stats.incrementProcessorQueriesCounter();

					if (randProc.getCurrentLoad() < config.loadThreshold_p)
					{
						randProc.addProcess(process);
						return;
					}
				}
			}

			
			processList.Add(process);
		}
	}
}
