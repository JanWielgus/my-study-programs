using System;
using System.Collections.Generic;
using System.Diagnostics.CodeAnalysis;
using System.Text;

namespace SO_1_ProcessorSimulator
{
    class ProcessComparer : IComparer<Process>
    {
        public int Compare([AllowNull] Process x, [AllowNull] Process y)
        {
            return x.TotalExecutionTime - y.TotalExecutionTime;
        }
    }
}
