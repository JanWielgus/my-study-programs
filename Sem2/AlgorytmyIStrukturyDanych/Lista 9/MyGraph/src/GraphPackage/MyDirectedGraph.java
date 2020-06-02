package GraphPackage;

import java.util.*;

public class MyDirectedGraph<V extends Comparable<V>> implements MyGraphInterface<V>
{
    protected Map<V, List<WeightDestVertex> > adjacencyMatrix;
    protected Edge.DirectionType directionType; // can only be changed in constructor


    public MyDirectedGraph()
    {
        adjacencyMatrix = new HashMap<>();
        directionType = Edge.DirectionType.DIRECTED;
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
                return new Edge<>(sourceVertex, destinationVertex, destVertex.getWeight(), directionType);
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
                edgeList.add(new Edge<>(sourceVertex, destVertex.getDestVertex(), destVertex.getWeight(), directionType));
        }

        return edgeList;
    }


    @Override
    public boolean hasEdge(V source, V destination)
    {
        return adjacencyMatrix.get(source).contains(destination);
    }


    // Dijkstra single source shortest path algorithm
    @Override
    public List<Path<V>> getShortestPathsFromSource(V source)
    {
        List<Path<V>> resultPathList = new ArrayList<>();
        //List<Float> tempLengthList = new ArrayList<>(); // array with potentially shortest path, infinity at the beginning
        List<V> vertexList = getVertexList();
        List<V> completedVertexList = new ArrayList<>();

        // Check if vertex list contain the given source
        if (!checkIfContainVertex(vertexList, source))
            throw new IllegalStateException("This graph do not contain such source vertex");

        // add source vertex to completed vertex list
        completedVertexList.add(source);

        // setup all other vertexes for searching
        int tempIndex = 0;
        for (V curVertex: vertexList)
        {
            // Skip source vertex
            if (curVertex.compareTo(source) == 0)
                continue;

            Edge<V> tempEdge = getEdge(source, curVertex);
            if (tempEdge != null) // if exist
                tempLengthList.add(tempEdge.getWeight());
            else
                tempLengthList.add(Float.MAX_VALUE); // infinity

            resultPathList.add(new Path<>(source));
        }


        while (completedVertexList.size() != vertexList.size())
        {
            // find vertex with shortest past path
            int smallestID = 0;
            for (int i=1; i<tempLengthList.size(); i++)
            {
                if (tempLengthList.get(i).compareTo(tempLengthList.get(smallestID)) < 0)
                    smallestID = i;
            }

            // add this vertex to completed array
            completedVertexList.add()
        }

        return null;
    }


    @Override
    public Path<V> getShortestPath(V source, V destination)
    {
        return null;
    }


    @Override
    public Edge.DirectionType getGraphType()
    {
        return directionType;
    }




    protected boolean checkIfContainVertex(List<V> list, V vertex)
    {
        for (V ver: list)
            if (ver.compareTo(vertex) == 0)
                return true;
        return false;
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
