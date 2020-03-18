using System;
using System.Collections.Generic;
using System.Text;

namespace SO_2_HDD_DriveSimulator
{
    class Sum
    {
        int totalSum;

        public Sum()
        {
            reset();
        }

        public void add(int value)
        {
            totalSum += value;
        }

        public int getSum()
        {
            return totalSum;
        }


        public void reset()
        {
            totalSum = 0;
        }
    }
}
