package GraphPackage;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyDirectedGraph<V> implements MyGraphInterface<V>
{
    private Map<V, List<WeightDestVertex> > adjacencyMatrix;


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
