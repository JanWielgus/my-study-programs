package GraphPackage;

import java.util.*;

public class MyDirectedGraph<V extends Comparable<V>> implements MyGraphInterface<V>
{
    protected Map<V, List<WeightDestVertex> > adjacencyList;
    protected Edge.DirectionType directionType; // can only be changed in constructor


    public MyDirectedGraph()
    {
        adjacencyList = new HashMap<>();
        directionType = Edge.DirectionType.DIRECTED;
    }


    @Override
    public void addVertex(V vertex)
    {
        if (vertex == null)
            throw new NullPointerException("Passed vertex cannot be null");

        adjacencyList.put(vertex, new LinkedList<>());
    }


    @Override
    public void addEdge(V source, V destination, float weight)
    {
        if (source == null || destination == null)
            throw new NullPointerException("Any vertex cannot be null");

        // check if this vertexes exists in graph
        if (!adjacencyList.containsKey(source) || !adjacencyList.containsKey(destination))
            throw new IllegalArgumentException("There are no such vertexes to create an edge");

        // create new edge in the adjacency matrix
        if (!hasEdge(source, destination)) // check if this edge don't exist already
            adjacencyList.get(source).add(new WeightDestVertex(destination, weight));
    }


    @Override
    public Edge<V> getEdge(V sourceVertex, V destinationVertex)
    {
        if (sourceVertex == null || destinationVertex == null)
            throw new NullPointerException("Passed vertexes cannot be null");

        // Find list of destination vertexes from this source vertex
        List<WeightDestVertex> destVertexesList = adjacencyList.get(sourceVertex);

        if (destVertexesList == null)
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
        return new ArrayList<>(adjacencyList.keySet());
    }


    @Override
    public List<Edge<V>> getEdgeList()
    {
        List<Edge<V>> edgeList = new ArrayList<>();

        for (V sourceVertex: adjacencyList.keySet())
        {
            // Convert adjacency matrix to edge list
            for (WeightDestVertex destVertex: adjacencyList.get(sourceVertex))
                edgeList.add(new Edge<>(sourceVertex, destVertex.getDestVertex(), destVertex.getWeight(), directionType));
        }

        return edgeList;
    }


    @Override
    public List<V> getAdjacentVertexesList(V source)
    {
        if (source == null)
            throw new NullPointerException("Source vertex cannot be null");

        List<V> adjacentVertexesList = new ArrayList<>();
        List<WeightDestVertex> destinationVertexes = adjacencyList.get(source);

        if (destinationVertexes == null)
            throw new IllegalArgumentException("Passed source vertex doesn't exist in graph");

        // copy adjacent vertexes from source vertex to new array
        for (WeightDestVertex destVertex: destinationVertexes)
            adjacentVertexesList.add(destVertex.getDestVertex());

        return adjacentVertexesList;
    }

    @Override
    public List<Edge<V>> getAdjacentEdgeList(V source)
    {
        if (source == null)
            throw new NullPointerException("Source vertex cannot be null");

        List<Edge<V>> adjacentEdgeList = new ArrayList<>();
        List<WeightDestVertex> destinationVertexes = adjacencyList.get(source);

        if (destinationVertexes == null)
            throw new IllegalArgumentException("Passed source vertex doesn't exist in graph");

        // create adjacent edges list
        for (WeightDestVertex destVertex: destinationVertexes)
            adjacentEdgeList.add(new Edge<>(source, destVertex.getDestVertex(), destVertex.getWeight(), getGraphType()));

        return adjacentEdgeList;
    }


    @Override
    public boolean hasEdge(V source, V destination)
    {
        return adjacencyList.get(source).contains(destination);
    }


    // Dijkstra single source shortest path algorithm
    @Override
    public List<Path<V>> getShortestPathsFromSource(V source)
    {
        if (source == null)
            throw new NullPointerException("Source vertex cannot be null");

        List<V> uncheckedVertexList = getVertexList();
        List<V> checkedVertexList = new ArrayList<>();
        Map<V, Float> pathsWeights = new HashMap<>();
        Map<V, Path<V>> pathMap = new HashMap<>();

        // Prepare
        for (V vertex: uncheckedVertexList)
        {
            // set paths weights to infinity except for source vertex
            pathsWeights.put(vertex, Float.POSITIVE_INFINITY);
            pathsWeights.replace(source, 0.0f);

            // create new path for each vertex
            // and set source to source vertex
            Path<V> newPath = new Path<>();
            newPath.setSource(source);
            pathMap.put(vertex, newPath);
        }


        while (uncheckedVertexList.size() > 0)
        {
            // find vertex with the lowest pathWeight (from unchecked vertex list)
            float lowestWeight = Float.POSITIVE_INFINITY;
            V lowestWeightVertex = null;
            for (V vert: uncheckedVertexList)
            {
                float curWeight = pathsWeights.get(vert);
                if (curWeight < lowestWeight)
                {
                    lowestWeight = curWeight;
                    lowestWeightVertex = vert;
                }
            }

            // move found vertex from unchecked to checked vertex list
            uncheckedVertexList.remove(lowestWeightVertex);
            checkedVertexList.add(lowestWeightVertex);


            // for all adjacent vertexes with lowest weight vertex
            List<V> adjacentVertexList = getAdjacentVertexesList(lowestWeightVertex);
            float toLowestVertPathWeight = pathsWeights.get(lowestWeightVertex);
            for (V adjacentVert: adjacentVertexList)
            {
                // skip for checked adjacent vertexes
                if (checkedVertexList.contains(adjacentVert))
                    continue;

                float adjacentVertPathWeight = pathsWeights.get(adjacentVert);
                Edge<V> checkedEdge = getEdge(lowestWeightVertex, adjacentVert); // edge from lowest weight vertex to current adjacent vertex
                float potentialNewPathWeight = toLowestVertPathWeight + checkedEdge.getWeight();
                // Check if path through current lowestWeightVertex is shorter
                if (adjacentVertPathWeight > potentialNewPathWeight)
                {
                    // Set adjacent vertex pathWeight to new lower value
                    pathsWeights.replace(adjacentVert, potentialNewPathWeight);

                    // Set new path (through lowestWeightVertex)
                    Path<V> tempPath = pathMap.get(adjacentVert);
                    tempPath.setPath(pathMap.get(lowestWeightVertex));
                    tempPath.addEdge(checkedEdge);
                }
            }
        }


        // Generate results
        List<Path<V>> pathList = new ArrayList<>(pathMap.values());
        // remove path from source to source
        for (int i=0; i<pathList.size(); i++)
        if (pathList.get(i).getSource().compareTo(source) == 0)
        {
            pathList.remove(i);
            break;
        }

        return pathList;
    }


    @Override
    public Path<V> getShortestPath(V source, V destination)
    {
        if (source == null)
            throw new NullPointerException("Source vertex cannot be null");
        if (destination == null)
            throw new NullPointerException("Destination vertex cannot be null");

        List<Path<V>> shortestPathsFromSource = getShortestPathsFromSource(source);

        // find path with proper destination
        for (Path<V> path: shortestPathsFromSource)
            if (path.getDestination().compareTo(destination) == 0)
                return path;

        // If not found such path
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
