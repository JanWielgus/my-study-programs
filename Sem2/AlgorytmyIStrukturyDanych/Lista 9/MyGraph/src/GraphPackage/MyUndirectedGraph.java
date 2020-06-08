package GraphPackage;

import DisjointSetsPackage.DisjointSets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyUndirectedGraph <V extends Comparable<V>> extends MyDirectedGraph <V>
{
    public MyUndirectedGraph()
    {
        super();
        directionType = Edge.DirectionType.UNDIRECTED; // override direction type
    }


    @Override
    public void addEdge(V source, V destination, float weight)
    {
        // Add edges in both directions
        super.addEdge(source, destination, weight);
        super.addEdge(destination, source, weight);
    }



    // Kruskal's Minimum Spanning Tree algorithm
    public List<Edge<V>> getMinimumSpanningTree()
    {
        List<Edge<V>> resultEdgeList = new ArrayList<>();
        List<Edge<V>> allEdgeList = new ArrayList<>(); // list of all edges (from adjacency matrix)
        DisjointSets<V> disjointSets = new DisjointSets<V>();

        // put each vertex to separate set
        for (V sourceVertex: adjacencyList.keySet())
        {
            disjointSets.makeSet(sourceVertex);

            // Convert adjacency matrix to edge list
            for (WeightDestVertex destVertex: adjacencyList.get(sourceVertex))
                allEdgeList.add(new Edge<>(sourceVertex, destVertex.getDestVertex(), destVertex.getWeight(), Edge.DirectionType.UNDIRECTED));
        }

        // Sort edges by weight ascending
        Collections.sort(allEdgeList);

        for (int i=0; i<allEdgeList.size(); i++)
        {
            Edge<V> curEdge = allEdgeList.get(i);

            if (disjointSets.findSet(curEdge.getSource()) != disjointSets.findSet(curEdge.getDestination()))
            {
                resultEdgeList.add(curEdge);
                disjointSets.union(curEdge.getSource(), curEdge.getDestination());
            }
        }

        return resultEdgeList;
    }

}
