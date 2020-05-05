using System;
using System.Collections.Generic;
using System.Diagnostics.CodeAnalysis;
using System.Text;

namespace SO_4
{
    class Page
    {
        public int nr;
        public int proces;
        public int reff;

        public static IComparer<Page> refComparer = new RefComparer();



        public Page(int nr, int reff, int proces)
        {
            this.nr = nr;
            this.proces = proces;
            this.reff = reff;
        }


        public Page(Page p)
        {
            this.nr = p.nr;
            this.proces = p.proces;
            this.reff = p.reff;
        }


        public void setRef(int reff)
        {
            this.reff = reff;
        }


        public string toString()
        {
            return nr + " ";
        }




        class RefComparer : IComparer<Page>
        {
            public int Compare([AllowNull] Page x, [AllowNull] Page y)
            {
                return x.reff - y.reff;
            }
        }
    };

}
