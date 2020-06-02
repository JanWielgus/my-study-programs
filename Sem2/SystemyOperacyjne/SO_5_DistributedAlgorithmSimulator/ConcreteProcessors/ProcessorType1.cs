using System;
using System.Collections.Generic;
using System.Text;

/*
	Na procesorze x pojawia sie zadanie. Nastepnie:

	1. x pyta losowo wybr. procesor y o aktualne obciążenie.
	Jeśli jest mniejsze od progu p, proces jest tam wysyłany.
	Jeśli nie, losujemy i pytamy następny, próbując co najwyżej z razy.
	Jeśli wszystkie wylosowane są obciążone powyżej p, proces wykonuje się na x. 
*/


namespace SO_5_DistributedAlgorithmSimulator.ConcreteProcessors
{
	class ProcessorType1 : Processor
	{
		public ProcessorType1(ProcessorManager processorManager) : base(processorManager)
		{
		}

		public override void addProcess(Process process)
		{
			for (int i=0; i<config.maxDismissAttempts_z; i++)
			{
				Processor randomProc = processorManager.getRandomProcessor(); // losowy procesor y

				// update stats
				stats.incrementProcessorQueriesCounter();

				if (randomProc.getCurrentLoad() < config.loadThreshold_p)
				{
					randomProc.addProcess(process);
					return;
				}
			}

			// If was not already sent to different processor, add to this processor
			processList.Add(process);
		}
	}
}
