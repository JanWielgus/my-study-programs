using System;
using System.Collections.Generic;
using System.Text;

namespace SO_4
{
	class Proces
	{
        public List<Page> proces;
        public int PFrame;
        public int PPF = 0;
        public int FRAME_SIZE;
        List<Page> Frame = new List<Page>();

        public int PF = 0;



        public Proces(List<Page> proces, int PFrame)
        {
            this.PFrame = PFrame;
            this.proces = proces;

        }


        public Proces(Proces p)
        {
            this.proces = p.proces;
            this.PFrame = p.PFrame;
        }


        public void setFrame(int frame)
        {
            FRAME_SIZE = frame;
        }


        public void setPPF(int PPF)
        {
            this.PPF = PPF;
        }


        public void setProces(List<Page> proces)
        {
            this.proces = proces;
        }



        public int LRU(List<Page> PageReferences)
        {
            int PF = 0;
            List<Page> Pages2 = new List<Page>();
            foreach (Page p in PageReferences)
                Pages2.Add(new Page(p));

            Page n;

            n = Pages2[0];


            if (Frame.Count < FRAME_SIZE)
            {
                foreach (Page p in Frame)
                {
                    if (p.nr == n.nr)
                    {
                        p.setRef(p.reff + 1);
                        break;
                    }
                }
                PF++;
                Frame.Add(n);
            }
            else
            {
                foreach (Page p in Frame)
                {
                    if (p.nr == n.nr)
                    {
                        p.setRef(p.reff +1);
                        break;
                    }
                }

                Frame.Sort(Page.refComparer);

                Frame.RemoveAt(0);
                Frame.Add(n);
                PF++;

            }
            //  System.out.println("bang" +Frame+"pf: " + PF);
            setPPF(PPF + PF);
            return PF;
        }


    }
}
