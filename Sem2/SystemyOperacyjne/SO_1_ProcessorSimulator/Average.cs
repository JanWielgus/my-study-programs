using System;
using System.Collections.Generic;
using System.Text;

namespace SO_1_ProcessorSimulator
{
    class Average
    {
        private int sum;
        private int amt;

        public Average()
        {
            sum = 0;
            amt = 0;
        }

        public void addNewValue(int value)
        {
            sum += value;
            amt++;
        }

        public float getAverage()
        {
            if (amt > 0)
                return sum / amt;
            else
                return -1;
        }
    }
}
