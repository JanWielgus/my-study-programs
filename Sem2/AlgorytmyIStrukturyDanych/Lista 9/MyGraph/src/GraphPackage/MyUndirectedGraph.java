package GraphPackage;

import DisjointSetsPackage.DisjointSets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MyUndirectedGraph <V extends Comparable> extends MyDirectedGraph <V>
{
    public MyUndirectedGraph()
    {
        super();
    }


    @Override
    public void addEdge(V source, V destination, float weight)
    {
        // Add edges in both directions
        super.addEdge(source, destination, weight);
        super.addEdge(destination, source, weight);
    }



    public List<Edge> getMinimumSpanningTree()
    {
        List<Edge> resultEdgeList = new ArrayList<>();
        List<Edge> allEdgeList = new ArrayList<>(); // list of all edges (from adjacency matrix)
        DisjointSets<V> disjointSets = new DisjointSets<V>();

        // put each vertex to separate set
        for (V sourceVertex: adjacencyMatrix.keySet())
        {
            disjointSets.makeSet(sourceVertex);

            // Convert adjacency matrix to edge list
            for (WeightDestVertex destVertex: adjacencyMatrix.get(sourceVertex))
                allEdgeList.add(new Edge(sourceVertex, destVertex.getDestVertex(), destVertex.getWeight()));
        }

        // Sort edges by weight ascending
        Collections.sort(allEdgeList);

        for (int i=0; i<allEdgeList.size(); i++)
        {
            Edge curEdge = allEdgeList.get(i);

            if (disjointSets.findSet(curEdge.getSource()) != disjointSets.findSet(curEdge.getDestination()))
            {
                resultEdgeList.add(curEdge);
                disjointSets.union(curEdge.getSource(), curEdge.getDestination());
            }
        }

        return resultEdgeList;
    }



/*
    public class Edge implements Comparable<Edge> {
        private V source;
        private V destination;
        private float weight;

        public Edge(V source, V destination, float weight)
        {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        public V getSource()
        {
            return source;
        }

        public V getDestination()
        {
            return destination;
        }

        public float getWeight()
        {
            return weight;
        }


        @Override
        public int compareTo(Edge o)
        {
            boolean temp1 = source == o.source && destination == o.destination;
            boolean temp2 = source == o.destination && destination == o.source;

            if (temp1 || temp2)
                return 0;
            else
                return (int)(weight - o.weight + 0.5f);
        }
    }*/
}
