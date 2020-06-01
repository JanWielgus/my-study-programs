package GraphPackage;

import java.util.*;

public class MyDirectedGraph<V> implements MyGraphInterface<V>
{
    protected Map<V, List<WeightDestVertex> > adjacencyMatrix;


    public MyDirectedGraph()
    {
        adjacencyMatrix = new HashMap<>();
    }


    @Override
    public void addVertex(V vertex)
    {
        if (vertex == null)
            throw new NullPointerException("Passed vertex cannot be null");

        adjacencyMatrix.put(vertex, new LinkedList<>());
    }


    @Override
    public void addEdge(V source, V destination, float weight)
    {
        if (source == null || destination == null)
            throw new NullPointerException("Any vertex cannot be null");

        // check if this vertexes exists in graph
        if (!adjacencyMatrix.containsKey(source) || !adjacencyMatrix.containsKey(destination))
            throw new IllegalArgumentException("There are no such vertexes to create an edge");

        // create new edge in the adjacency matrix
        if (!hasEdge(source, destination)) // check if this edge don't exist already
            adjacencyMatrix.get(source).add(new WeightDestVertex(destination, weight));
    }


    @Override
    public Edge<V> getEdge(V sourceVertex, V destinationVertex)
    {
        // Find list of destination vertexes from this source vertex
        List<WeightDestVertex> destVertexesList = adjacencyMatrix.get(sourceVertex);

        if (destinationVertex == null)
            return null;

        // Find destination vertex if exist
        for (WeightDestVertex destVertex: destVertexesList)
        {
            if (destVertex.getDestVertex() == destinationVertex)
                return new Edge<>(sourceVertex, destinationVertex, destVertex.getWeight());
        }

        return null;
    }


    @Override
    public List<V> getVertexList()
    {
        return new ArrayList<>(adjacencyMatrix.keySet());
    }


    @Override
    public List<Edge<V>> getEdgeList()
    {
        List<Edge<V>> edgeList = new ArrayList<>();

        for (V sourceVertex: adjacencyMatrix.keySet())
        {
            // Convert adjacency matrix to edge list
            for (WeightDestVertex destVertex: adjacencyMatrix.get(sourceVertex))
                edgeList.add(new Edge<>(sourceVertex, destVertex.getDestVertex(), destVertex.getWeight()));
        }

        return edgeList;
    }


    @Override
    public boolean hasEdge(V source, V destination)
    {
        return adjacencyMatrix.get(source).contains(destination);
    }






    protected class WeightDestVertex // weighted destination vertex
    {
        private V vertex; // destination vertex
        private float weight;

        public WeightDestVertex(V vertex, float weight)
        {
            this.vertex = vertex;
            this.weight = weight;
        }

        public V getDestVertex()
        {
            return vertex;
        }

        public float getWeight()
        {
            return weight;
        }
    }
}
