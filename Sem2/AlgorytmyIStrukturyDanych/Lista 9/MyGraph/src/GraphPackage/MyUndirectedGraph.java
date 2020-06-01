package GraphPackage;

import DisjointSetsPackage.DisjointSets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MyUndirectedGraph <V extends Comparable<V>> extends MyDirectedGraph <V>
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



    // Kruskal's Minimum Spanning Tree algorithm
    public List<Edge> getMinimumSpanningTree()
    {
        List<Edge> resultEdgeList = new ArrayList<>();
        List<UndirectedEdge> allEdgeList = new ArrayList<>(); // list of all edges (from adjacency matrix)
        DisjointSets<V> disjointSets = new DisjointSets<V>();

        // put each vertex to separate set
        for (V sourceVertex: adjacencyMatrix.keySet())
        {
            disjointSets.makeSet(sourceVertex);

            // Convert adjacency matrix to edge list
            for (WeightDestVertex destVertex: adjacencyMatrix.get(sourceVertex))
                allEdgeList.add(new UndirectedEdge<>(sourceVertex, destVertex.getDestVertex(), destVertex.getWeight()));
        }

        // Sort edges by weight ascending
        Collections.sort(allEdgeList);

        for (int i=0; i<allEdgeList.size(); i++)
        {
            UndirectedEdge<V> curEdge = allEdgeList.get(i);

            if (disjointSets.findSet(curEdge.getSource()) != disjointSets.findSet(curEdge.getDestination()))
            {
                resultEdgeList.add(curEdge);
                disjointSets.union(curEdge.getSource(), curEdge.getDestination());
            }
        }

        return resultEdgeList;
    }




    private static class UndirectedEdge <V> extends Edge<V> implements Comparable<UndirectedEdge>
    {
        public UndirectedEdge(V source, V destination, float weight) {
            super(source, destination, weight);
        }


        @Override
        public int compareTo(UndirectedEdge o)
        {
            boolean temp1 = getSource() == o.getSource() && getDestination() == o.getDestination();
            boolean temp2 = getSource() == o.getDestination() && getDestination() == o.getSource();

            if (temp1 || temp2)
                return 0;

            return getWeight() > o.getWeight() ? 1 : -1;
        }
    }
}
