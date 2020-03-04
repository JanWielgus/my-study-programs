using System;
using System.Collections.Generic;
using System.Text;

namespace SO_1_ProcessorSimulator
{
    class ProgramTimer
    {
        private static int time;


        public static int getTime()
        {
            return time;
        }


        // Processor execute this method
        public static void tick(int tickTime)
        {
            time += tickTime;
        }


        public static void reset()
        {
            time = 0;
        }
    }
}
