using System;
using System.Collections.Generic;
using System.Text;

namespace SO_4
{
	class Algorithms
	{

        public int FrameSize;
        public int AmtOfPages;
        public int Processes;
        public int interval;
        public List<Page> ProcessList;
        public Proces[] ProcessesTab;

        List<Page> PageReferences = new List<Page>();
        List<Page> Frame = new List<Page>();
        public int PF = 0;

       
        public Algorithms(int frameSize, int amtOfPages, int interval, int Processes)
        {
            this.FrameSize = frameSize;
            this.AmtOfPages = amtOfPages;
            this.Processes = Processes;
            this.interval = interval;
            ProcessesTab = new Proces[Processes];


            // generate random
            for (int a = 0; a < amtOfPages; a++)
            {
                Random rand = new Random();
                int k = (int)(rand.NextDouble() * Processes);
                int r = (int)(rand.NextDouble() * interval);
                if ((k == 3 || k == 5) && k % 3 != 0)
                {
                    k = 8;
                }
                // creating page references
                PageReferences.Add(new Page(r, 0, k));

            }
            // creating table of processes
            for (int w = 0; w < Processes; w++)
            {
                ProcessesTab[w] = new Proces(new List<Page>(), 0);
                for (int s = 0; s < PageReferences.Count; s++)
                {
                    if ((PageReferences[s]).proces == w)
                    {
                        Proces a = ProcessesTab[w];
                        a.proces.Add(PageReferences[s]);                     
                    }
                }
            }


        }

        


        public int Equal()
        {
            //copying references
            Proces[] ProcessesTabCopy = new Proces[Processes];
            int PF_SUM = 0;
            //frame size as a whole frames divided by processes length
            int frame_size = FrameSize / (ProcessesTab.Length);
            //adds all page faults
            for (int k = 0; k < ProcessesTab.Length; k++)
            {
                ProcessesTabCopy[k] = new Proces(ProcessesTab[k]);
                int p = LRU(ProcessesTabCopy[k].proces, frame_size);
                PF_SUM += p;

            }

            return PF_SUM;
        }



        public int Proportional()
        {
            //frame size as a whole frames divided by processes length
            int frame_size = FrameSize / ProcessesTab.Length;
            Proces[] ProcessesTabCopy = new Proces[Processes];
            int PF_SUM = 0;
            //copying page references
            for (int k = 0; k < ProcessesTab.Length; k++)
            {
                ProcessesTabCopy[k] = new Proces(ProcessesTab[k]);
                // ProcessesTabCopy[k].setFrame(frame_size);

            }
            for (int j = 0; j < ProcessesTabCopy.Length; j++)
            {

                //frame size: depends on proces size
                frame_size = ProcessesTabCopy[j].proces.Count * FrameSize / AmtOfPages;
                //minimal frame size
                if (frame_size == 0)
                {
                    frame_size = 3;
                }
                int p = LRU(ProcessesTabCopy[j].proces, frame_size);
                PF_SUM += p;
            }
            return PF_SUM;
        }




        public int PageFaultSteering()
        {
            //max page faults for starting working algorithm
            int PFMax = (int)0.6 * AmtOfPages;
            //starting page size
            int frame_size = FrameSize / ProcessesTab.Length;
            //copying page rreferences
            Proces[] ProcessesTabCopy = new Proces[Processes];
            for (int k = 0; k < ProcessesTab.Length; k++)
            {
                ProcessesTabCopy[k] = new Proces(ProcessesTab[k]);
                ProcessesTabCopy[k].setFrame(frame_size);
                //    System.out.println(ProcessesTabCopy[k].proces);
            }
            int freeFrames = 0;
            bool allDone = false;
            int size = Processes;
            int PFGlobal = 0;
            while (size != 0)
            {
                int min = interval;
                int max = 0;
                //index of proces which generates minimal page faults
                int minI = 0;
                //index of proces which generates maximal page faults
                int maxI = 0;
                for (int i = 0; i < ProcessesTabCopy.Length; i++)
                {
                    Proces t = ProcessesTabCopy[i];
                    if (t != null && t.proces.Count != 0)
                    {
                        if (size == 1)
                        {
                            ProcessesTabCopy[i].setFrame(ProcessesTabCopy[i].FRAME_SIZE + freeFrames);
                            freeFrames = 0;
                        }

                        int pf = t.PPF;

                        //single page fault
                        int pfsingle = t.LRU(t.proces);

                        //changing indexes of min and max
                        if (pf > max)
                        {
                            max = pf;
                            maxI = i;
                        }

                        if (pf < min)
                        {
                            min = pf;
                            minI = i;
                        }

                        t.proces.RemoveAt(0);
                        PFGlobal += pfsingle;
                    }
                    else if (t != null)
                    {
                        if (ProcessesTabCopy[maxI] != null && maxI != i)
                        {
                            //if process is done
                            ProcessesTabCopy[maxI].setFrame(ProcessesTabCopy[maxI].FRAME_SIZE + ProcessesTabCopy[i].FRAME_SIZE);
                        }
                        else
                        {
                            freeFrames += ProcessesTabCopy[i].FRAME_SIZE;
                        }
                        ProcessesTabCopy[i] = null;
                        size--;
                    }

                }
                //if page faults reach edge
                if (ProcessesTabCopy[minI] != null && ProcessesTabCopy[maxI] != null && ProcessesTabCopy[minI].PFrame != 1
                        && max > PFMax)
                {
                    if (ProcessesTabCopy[minI].FRAME_SIZE > 3)
                    {
                        //substracting frame from proces which generates too few page faults
                        ProcessesTabCopy[minI].setFrame(ProcessesTabCopy[minI].FRAME_SIZE - 1);
                        //adding frame for proces which generates too many page faults
                        ProcessesTabCopy[maxI].setFrame(ProcessesTabCopy[maxI].FRAME_SIZE + 1 + freeFrames);
                        freeFrames = 0;
                    }
                }
            }

            return PFGlobal;
        }




        public int ZoneModel(int zone)
        {
            int PFGlobal = 0;
            int freeFrames = FrameSize;
            int allDone = -1;

            //copying page references
            Proces[] ProcessesTabCopy = new Proces[Processes];
            for (int k = 0; k < ProcessesTab.Length; k++)
            {
                ProcessesTabCopy[k] = new Proces(ProcessesTab[k]);

                //frame size as a number of duplications in zone
                int frame_size = numberOfDuplications(ProcessesTabCopy[k].proces, zone);
                //setting frame size to proces
                ProcessesTabCopy[k].setFrame(frame_size);


            }


            do
            {
                for (int k = allDone + 1; k < ProcessesTab.Length; k++)
                {
                    //if there are free frames
                    if (freeFrames > ProcessesTabCopy[k].FRAME_SIZE)
                    {
                        allDone++;
                        int w = ProcessesTabCopy[k].FRAME_SIZE;
                        //substracting allocated frames
                        freeFrames -= w;
                        if (ProcessesTabCopy[k].FRAME_SIZE != 0)
                        {
                            int h = LRU(ProcessesTabCopy[k].proces, ProcessesTabCopy[k].FRAME_SIZE);

                            PFGlobal += h;
                        }

                    }


                }
                freeFrames = FrameSize;
            }
            //waiting processes
            while (allDone != Processes - 1);

            return PFGlobal;
        }




        public int LRU(List<Page> PagesRef, int FRAME_SIZE)
        {
            PF = 0;
            List<Page> Pages2 = new List<Page>();

            foreach (Page p in PagesRef)
            {
                Pages2.Add(new Page(p));
            }

            if (Pages2.Count == 0)
            {
                return 0;
            }

            Page n;
            for (int i = 0; i < Pages2.Count; i++)
            {
                n = Pages2[i];


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
                            p.setRef(p.reff + 1);
                            break;
                        }
                    }

                    Frame.Sort(Page.refComparer);

                    Frame.RemoveAt(0);
                    Frame.Add(n);
                    PF++;

                }

            }

            Frame.Clear();
            return PF;
        }





        public int numberOfDuplications(List<Page> a, int zone)
        {
            HashSet<int> h = new HashSet<int>();
            if (zone > a.Count)
            {
                zone = a.Count;
            }

            for (int i = 0; i < zone; i++)
            {
                h.Add(a[i].nr);
            }

            return h.Count;
        }
    }
}
