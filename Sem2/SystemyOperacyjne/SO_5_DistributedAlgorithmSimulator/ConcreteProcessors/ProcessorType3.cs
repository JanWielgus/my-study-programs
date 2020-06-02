using System;
using System.Collections.Generic;
using System.Text;


/*
	Na procesorze x pojawia sie zadanie. Nastepnie:

	3.Jak w pkt 2, z tym że procesory o obciążeniu mniejszym od minimalnego progu r
	pytają losowo wybrane procesory i jesli obc. zapytanego jest większe od p,
	pytający przejmuje część jego zadań (założyć jaką). 
*/



namespace SO_5_DistributedAlgorithmSimulator.ConcreteProcessors
{
	class ProcessorType3 : ProcessorType2
	{
		public ProcessorType3(ProcessorManager processorManager) : base(processorManager)
		{
		}


		public override void addProcess(Process process)
		{
			// same as type 2
			base.addProcess(process);

			// ask random procesor to give some processes if curLoad is less than r
			if (getCurrentLoad() < config.askingThreshold_r)
			{
				Processor randProc = processorManager.getRandomProcessor();

				// if this processor load is less than p, take over some processes
				if (randProc.getCurrentLoad() > config.loadThreshold_p)
				{
					List<Process> capturedList = randProc.transferSomeProcesses();
					foreach (Process proc in capturedList)
						processList.Add(proc);
				}
			}
		}
	}
}
