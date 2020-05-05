using System;

namespace SO_4
{
	class Program
	{
		static void Main(string[] args)
		{
            /*
            Algotithms a = new Algotithms(1000, 10000, 250, 1000);
            System.out.println("Assumption of simulation: \nIn system has to be two more free frames than processes," +
                    "(minimal free frames == 3,\n Page References:10000, Frames:100, Processes:10" +
                    "\nsteering page faults: max page faults: 60% references\n ");
            */


            Algorithms algs = new Algorithms(1000, 10000, 250, 1000);
            Console.WriteLine("Symulacja: ");
            Console.WriteLine();


            Console.Write("Przydzial rowny: ");
            Console.Write(algs.Equal());
            Console.WriteLine();

            Console.Write("Przydzial proporcjonalny: ");
            Console.Write(algs.Proportional());
            Console.WriteLine();

            Console.Write("Model strefowy: ");
            Console.Write(algs.ZoneModel(30));
            Console.WriteLine();

            Console.Write("Sterowanie czestoscia bledow strony: ");
            Console.WriteLine(algs.PageFaultSteering());
        }
	}
}
